package com.rprelevic.ta.rml.framework.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class RedisConfiguration {

    private static final String EVENT_KEY_SET = "__keyevent@0__:set";

    @Autowired
    private RedisCacheMessageListener messageReceiver;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(messageReceiver.getObjectClassType()));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(messageReceiver.getObjectClassType()));
        redisTemplate.setConnectionFactory(connectionFactory());

        return redisTemplate;
    };

    private PatternTopic topic() {
        return new PatternTopic(EVENT_KEY_SET);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.addMessageListener(new MessageListenerAdapter(messageReceiver), topic());
        container.setTaskExecutor(Executors.newFixedThreadPool(4));
        return container;
    }

}
