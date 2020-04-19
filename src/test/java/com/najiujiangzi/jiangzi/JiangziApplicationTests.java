package com.najiujiangzi.jiangzi;

import com.najiujiangzi.jiangzi.config.EmailConfig;
import com.najiujiangzi.jiangzi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JiangziApplicationTests {
    @Autowired
    private EmailConfig emailConfig;

    @Test
    public void contextLoads() {
//        emailConfig.sendSimpleMail("dengjiang0201@foxmail.com", "验证码测试", "888888");
        emailConfig.sendSimpleMail("yul.huang@foxmail.com", "验证码测试", "888888");
    }

}
