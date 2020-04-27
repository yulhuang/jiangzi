package com.najiujiangzi.jiangzi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;

    private Jedis getResource() {
        return jedisPool.getResource();
    }

    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 添加
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        Jedis resource = getResource();
        String set = resource.set(key, value);
        close(resource);
        return set.equals("OK");
    }

    /**
     * 添加并设置有效时间
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setex(String key, String value, Integer time) {
        if (time == null) {
            return set(key, value);
        } else {
            Jedis resource = getResource();
            String setex = resource.setex(key, time, value);
            close(resource);
            return setex.equals("OK");
        }
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis resource = getResource();
        String s = resource.get(key);
        close(resource);
        return s;
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean exists(String... key) {
        Jedis resource = getResource();
        Long exists = resource.exists(key);
        close(resource);
        return exists.intValue() == key.length;
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public boolean del(String... key) {
        Jedis resource = getResource();
        Long del = resource.del(key);
        close(resource);
        return del.intValue() == key.length;
    }

    /**
     * 递增
     *
     * @param key
     * @param increment
     */
    public void incrby(String key, Long increment) {
        Jedis resource = getResource();
        resource.incrBy(key, increment);
        close(resource);
    }
}
