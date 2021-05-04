package com.rprelevic.ta.cds.service;

import framework.RemoteExecutionInterface;
import framework.SubscriberDetails;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CoreDomainLookupService {

    @Autowired
    private RedissonClient redissonClient;

    //@Cacheable("subscriberDetails") //TODO This might not be good idea
    public SubscriberDetails findServiceDetails(final String serviceName) {
        RMap<String, SubscriberDetails> serviceRegistry = redissonClient.getMap("service-registry");
        SubscriberDetails serviceDetails = serviceRegistry.get(serviceName);
        log.info("name: {}, activationQuery: {}, sub. class type: {}",
                serviceDetails.getName(), serviceDetails.getActivationQuery(), serviceDetails.getSubscribedClassType());

        return serviceDetails;
    }

    //@Cacheable("remoteServices")
    public RemoteExecutionInterface findRemoteService(final SubscriberDetails description) {
        RemoteInvocationOptions options = RemoteInvocationOptions.defaults().expectAckWithin(5, TimeUnit.SECONDS);
        RRemoteService service = redissonClient.getRemoteService(description.getName());
        return service.get(RemoteExecutionInterface.class, options);
    }

}
