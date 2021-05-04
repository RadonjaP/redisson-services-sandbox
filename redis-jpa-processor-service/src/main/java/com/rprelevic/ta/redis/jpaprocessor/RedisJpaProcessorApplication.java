package com.rprelevic.ta.redis.jpaprocessor;

import com.rprelevic.ta.redis.jpaprocessor.repository.entity.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.concurrent.Executors;

@Slf4j
@EnableCaching
@SpringBootApplication
public class RedisJpaProcessorApplication {

    public static void main(String[] args) {
        log.info("Starting application {}...", RedisJpaProcessorApplication.class);
        SpringApplication.run(RedisJpaProcessorApplication.class);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext
                .SerializationPair.fromSerializer(jacksonSerializer)).entryTtl(Duration.ofDays(30));
        return configuration;
    }


}
