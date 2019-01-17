package com.licenseservice.cache;

import com.licenseservice.Config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache {

    @Autowired
    ServiceConfig serviceConfig;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(serviceConfig.getRedisServer());
        factory.setPort(serviceConfig.getRedisPort());
        return factory;
    }

    @Bean
    public RedisTemplate<Long, Object> getRedisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate<Long, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

}
