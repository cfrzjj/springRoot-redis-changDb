package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDBChangeUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setDataBase(int num) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);
            this.redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
            connectionFactory.afterPropertiesSet();//此处需调用afterPropertiesSet刷新RedisClient
        }
    }
}