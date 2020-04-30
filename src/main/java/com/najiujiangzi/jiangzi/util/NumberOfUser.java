package com.najiujiangzi.jiangzi.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NumberOfUser {
    @Autowired
    private RedisUtil redisUtil;
    private static String newUserKey = "todayNewUser:" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    private static String loginUserKey = "todayLoginUser:" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

    //每日零点计数归零
    @Scheduled(cron = "0 1 0 * * ?")
    private void makeZero() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oldTime = LocalDateTime.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log.info(oldTime + "共增用户-->" + redisUtil.get("todayNewUser:" + formatter.format(oldTime)));
        log.info(oldTime + "登录总数-->" + redisUtil.get("todayLoginUser:" + formatter.format(oldTime)));
        newUserKey = "todayNewUser:" + formatter.format(now);
        loginUserKey = "todayLoginUser:" + formatter.format(now);
        redisUtil.setex(newUserKey, (int) TimeUnit.SECONDS.convert(2, TimeUnit.DAYS), "0");
        redisUtil.setex(loginUserKey, (int) TimeUnit.SECONDS.convert(2, TimeUnit.DAYS), "0");
    }

    public static String getNewUserKey() {
        return newUserKey;
    }

    public static String getLoginUserKey() {
        return loginUserKey;
    }
}
