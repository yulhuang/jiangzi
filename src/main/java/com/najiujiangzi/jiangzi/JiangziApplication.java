package com.najiujiangzi.jiangzi;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

@SpringBootApplication
@MapperScan("com.najiujiangzi.jiangzi.mappers")
@EnableCaching
public class JiangziApplication {

    protected final static Logger logger = LoggerFactory.getLogger(JiangziApplication.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String configFile = "/data/config/jiangzi/application.properties";
        if (new File(configFile).exists()) {
             System.setProperty("spring.config.location", configFile);
        }
        SpringApplication.run(JiangziApplication.class, args);
        long end = System.currentTimeMillis();
        logger.info("耗时-" + (start - end) + "-毫秒");
    }

}
