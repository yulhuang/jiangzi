package com.najiujiangzi.jiangzi;

import com.najiujiangzi.jiangzi.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JiangziApplicationTests {
    //    @Value("${isServer}")
    protected Boolean isServer;
    @Autowired
    private EmailService emailService;

    @Test
    public void contextLoads() throws MessagingException {
//        EmailService.sendSimpleMail("dengjiang0201@foxmail.com", "验证码测试", "888888");
//        EmailService.sendSimpleMail("yul.huang@foxmail.com", "验证码测试", "888888");
//        emailService.sendVerification("yul.huang@qq.com", "验证码", "888888");
        System.out.println(isServer);

    }

}
