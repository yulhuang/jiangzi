package com.najiujiangzi.jiangzi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

@SpringBootApplication
@MapperScan("com.najiujiangzi.jiangzi.mappers")
@EnableCaching
public class JiangziApplication {

    public static void main(String[] args) {
        String configFile = "/data/config/jiangzi/application.properties";
        if (new File(configFile).exists()) {
//             System.setProperty("spring.config.location", configFile);
        }
        SpringApplication.run(JiangziApplication.class, args);
    }

}
