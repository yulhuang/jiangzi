package com.najiujiangzi.jiangzi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.mail.MessagingException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JiangziApplicationTests {
    @Autowired
    private JedisPool jedisPool;


    @Test
    public void contextLoads() {
        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis);
//        System.out.println("ok");
    }

}
