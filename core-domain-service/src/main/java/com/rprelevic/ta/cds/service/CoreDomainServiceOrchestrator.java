package com.rprelevic.ta.cds.service;

import framework.DistributedObject;
import framework.RemoteExecutionInterface;
import framework.SubscriberDetails;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLiveObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoreDomainServiceOrchestrator {

    @Autowired
    private RLiveObjectService liveObjectService;

    @Autowired
    private CoreDomainLookupService lookupService;

    public <T extends DistributedObject> void process(final T object) {

        // find remote service details
        SubscriberDetails subscriberDetails = lookupService.findServiceDetails(object.getPayloadClassName());

        // find remote service using details
        RemoteExecutionInterface remoteExecution = lookupService.findRemoteService(subscriberDetails);

        // execute required functions
        log.info("Starting remote calls....");

        if (remoteExecution.check(object)) {
            log.info("check() is true...");
        } else {
            log.info("check() is false...");
        }

        DistributedObject updatedObject = remoteExecution.callback(object);

        log.info("Version after callback function: {}", updatedObject.getVersion());
        //log.info("Payload after callback function: {}", updatedObject.getPayload());

        // persist results
        // liveObjectService.persist(updatedObject);
    }

}
