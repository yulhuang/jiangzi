package com.najiujiangzi.jiangzi.rocketmq;

import com.najiujiangzi.jiangzi.service.EmailService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class Consumer {
    @Value("${rocketMQnameServer}")
    private String nameServerUrl;

    @Value("${isServer}")
    private boolean isserver;

    @Autowired
    private EmailService emailService;

    @PostConstruct
    public void createdEmailConsumer() throws Exception {
        DefaultMQPushConsumer consumer = createConsumer("emailGroup");
        if (consumer == null) {
            return;
        }
        consumer.subscribe("emailTopic", "emailTag");
        //广播模式（默认负载均衡模式）
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    byte[] body = messageExt.getBody();
                    String[] split = new String(body).split(",");
                    if (!CollectionUtils.isEmpty(Arrays.asList(split)) && (split.length == 3)) {
                        emailService.sendVerification(split[0], split[1], split[2]);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
            consumer.start();
    }

    private DefaultMQPushConsumer createConsumer(String group) {
        if (isserver) {
            return null;
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(nameServerUrl);
        return consumer;
    }
}
