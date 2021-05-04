package com.rprelevic.ta.cds.service;

import framework.DistributedObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoreDomainMessageReceiver<T extends DistributedObject> implements MessageListener<T> {

    @Autowired
    private CoreDomainServiceOrchestrator coreDomainServiceOrchestrator;

    //TODO: Make it Async
    @Override
    public void onMessage(CharSequence charSequence, T object) {
        log.info("onMessage activated. Object id: {}", object.getId());
        coreDomainServiceOrchestrator.process(object);
    }

}
