package com.najiujiangzi.jiangzi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.najiujiangzi.jiangzi.mappers")
@EnableCaching
public class JiangziApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiangziApplication.class, args);
    }

}
