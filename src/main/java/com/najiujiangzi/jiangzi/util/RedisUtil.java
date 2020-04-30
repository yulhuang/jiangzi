package com.najiujiangzi.jiangzi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;

    @Value("${isServer}")
    private boolean isServer;

    private Jedis getResource() {
        return isServer ? jedisPool.getResource() : null;
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
    public Boolean set(String key, String value) {
        Jedis resource = getResource();
        if (resource == null) {
            return null;
        }
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
    public Boolean setex(String key, Integer time, String value) {
        Jedis resource = getResource();
        if (resource == null) {
            return null;
        }
        String setex = resource.setex(key, time, value);
        close(resource);
        return setex.equals("OK");
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis resource = getResource();
        if (resource == null) {
            return null;
        }
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
    public Boolean exists(String... key) {
        Jedis resource = getResource();
        if (resource == null) {
            return null;
        }
        Long exists = resource.exists(key);
        close(resource);
        return exists.intValue() == key.length;
    }

    /**
     * 删除，不可模糊匹配
     *
     * @param key
     * @return
     */
    public Boolean del(String... key) {
        Jedis resource = getResource();
        if (resource == null) {
            return null;
        }
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
        if (resource == null) {
            return;
        }
        resource.incrBy(key, increment);
        close(resource);
    }

    /**
     * 删除key，可模糊匹配，如key*
     *
     * @param keys
     */
    public void delKeys(String keys) {
        Jedis resource = getResource();
        if (resource == null) {
            return;
        }
        Set<String> keysSet = resource.keys(keys);
        for (String s : keysSet) {
            resource.del(s);
        }
        close(resource);
    }
}
