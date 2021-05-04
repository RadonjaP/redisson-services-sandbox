package com.rprelevic.ta.cds.config;

import com.rprelevic.ta.cds.service.CoreDomainMessageReceiver;
import framework.DistributedObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class RedisConfiguration {

    //TODO: This will go to config file
    private static final String EVENT_KEY_SET = "live-object-topic";

    @Autowired
    private CoreDomainMessageReceiver coreDomainMessageReceiver;

    @Bean
    public RedissonClient redissonClient() {
        return Redisson.create(configuration());
    }

    @Bean
    public RLiveObjectService redissonLiveObjectService() {
        return redissonClient().getLiveObjectService();
    }

    @PostConstruct
    public void init() {
        RTopic topic = redissonClient().getTopic(EVENT_KEY_SET, new TypedJsonJacksonCodec(DistributedObject.class));
        topic.addListener(DistributedObject.class, coreDomainMessageReceiver);

    }

    public Config configuration() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        return config;
    }

}
