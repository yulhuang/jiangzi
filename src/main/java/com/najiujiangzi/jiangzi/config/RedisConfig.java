package com.najiujiangzi.jiangzi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int max_active;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int max_idle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int min_idle;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(max_idle);
        jedisPoolConfig.setMinIdle(min_idle);
        jedisPoolConfig.setMaxTotal(max_active);

        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }
}
