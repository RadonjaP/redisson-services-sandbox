package com.rprelevic.ta.payroll.processor;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RedisConfiguration {

    @Bean
    public RedissonClient redissonClient() {
        return Redisson.create(configuration());
    }

    private Config configuration() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

//        config.setCodec(new TypedJsonJacksonCodec(Participant.class));

        return config;
    }

}
