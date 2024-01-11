package com.w83ll43.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        // 默认的 Key 序列化器为 JdkSerializationRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key 序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // value 序列化

        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
