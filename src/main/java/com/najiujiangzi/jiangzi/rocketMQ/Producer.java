package com.najiujiangzi.jiangzi.rocketMQ;

import com.najiujiangzi.jiangzi.dto.EmailAndCodeDTO;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Value("${rocketMQnameServer}")
    private String nameServerUrl;


    /**
     * 发送同步消息
     */
    public void syncProducer() throws Exception {
        //创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = createdProducer("group1");
        //指定NameServer地址
//        producer.setNamesrvAddr("192.168.2.131:9876");
        //启动producer
//        producer.start();

        //创建消息对象，指定主题Topic，Tag和消息体
        for (int i = 0; i < 10; i++) {
            Message message = new Message("topic1", "tag1", ("同步发送测试-" + i + "-->" + LocalDateTime.now()).getBytes());
            //发送消息
            SendResult send = producer.send(message);
            System.out.println(send.getSendStatus());
        }

        //关闭生产者producer
        producer.shutdown();
    }

    /**
     * 发送异步消息
     *
     * @throws Exception
     */
    public void emailAsyncProducer(String email, String code) throws Exception {
        DefaultMQProducer producer = createdProducer("emailGroup");
        final boolean[] shutdown = {false};
        //发送失败后的重复发送次数
        //producer.setRetryTimesWhenSendAsyncFailed(0);
        Message message = new Message("emailTopic", "emailTag", (new EmailAndCodeDTO(email, "验证码", code).toString()).getBytes());
        producer.send(message, new SendCallback() {
            //发送成功回调函数
            @Override
            public void onSuccess(SendResult sendResult) {
                shutdown[0] = true;
                logger.info("邮箱验证码发送成功：" + email + "时间：" + LocalDateTime.now() + "回调信息：" + sendResult);
            }

            //发送失败回调函数
            @Override
            public void onException(Throwable throwable) {
                shutdown[0] = true;
                logger.error("邮箱验证码发送失败：" + email + "时间：" + LocalDateTime.now() + "失败信息：" + throwable);
            }
        });
        if (shutdown[0]) {
            producer.shutdown();
        }
    }

    /**
     * 单向发送
     */
    public static void oneWayProducer() {

    }

    public void orderProducer() throws Exception {
        DefaultMQProducer producer = createdProducer("orderGroup");

    }

    private DefaultMQProducer createdProducer(String groupName) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(nameServerUrl);
        producer.start();
        return producer;
    }
}
