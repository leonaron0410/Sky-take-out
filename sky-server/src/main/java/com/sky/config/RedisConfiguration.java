package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建redisTemplate对象...");
        RedisTemplate redisTemplate = new RedisTemplate();
        // 设置redisTemplate关联的连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //设置redisTemplate的key序列化器为StringRedisSerializer   使得key可以直观显示
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        log.info("创建redisTemplate对象成功");
        return redisTemplate;
    }
}
