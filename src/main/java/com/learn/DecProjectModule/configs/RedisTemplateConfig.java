package com.learn.DecProjectModule.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * Creates a RedisTemplate bean
     * Attaches a JedisConnectionFactory
     * Tells Spring how to connect to Redis
     * Connection is created lazily (only when Redis is actually used)
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplateConfig() {
        //Create an object of redisTemplate and define dataType of key,value
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // Setting the connection factory here
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }


    //1. An Object of JedisConnectionFactory : Manages the connection to redis
    //2. This bean creates and configures a RedisTemplate and links it to Redis via a connection factory
}
