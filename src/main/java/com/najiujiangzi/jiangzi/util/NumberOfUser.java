package com.najiujiangzi.jiangzi.util;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumberOfUser {
    //今日新增用户
    private static int todayCreateUser = 0;
    //今日登录过的用户
    private static int todayLoginCount = 0;

    private static Lock lock = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    //每日零点计数归零
    @Scheduled(cron = "0 0 0 * * ?")
    private void makeZero() {
        todayCreateUser = 0;
        todayLoginCount = 0;
    }

    public static int addTodayCreateUser() {
        lock.lock();
        try {
            ++todayCreateUser;
        } finally {
            lock.unlock();
        }
        return todayCreateUser;
    }

    public static int addTodayLoginCount() {
        lock2.lock();
        try {
            ++todayLoginCount;
        } finally {
            lock2.unlock();
        }
        return todayLoginCount;
    }
}
