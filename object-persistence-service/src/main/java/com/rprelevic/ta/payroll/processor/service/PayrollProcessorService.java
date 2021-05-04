package com.rprelevic.ta.payroll.processor.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PayrollProcessorService {

    //TODO: This will go in config file
    private static final String PUBLISH_TOPIC = "live-object-topic";

    @Autowired
    private RedissonClient redissonClient;

    public void process() {
        log.info("Message processing...");

        Participant participant = buildParticipant();

        // Notify CDS new object is added
        RTopic topic = redissonClient.getTopic(PUBLISH_TOPIC, new TypedJsonJacksonCodec(Participant.class)); //
        //DistributedObject saved = redissonClient.getLiveObjectService().merge(participant);
        topic.publish(participant);

        log.info("Message published...");
    }

    private Participant buildParticipant() {
        Participant participant = new Participant();
        participant.setId(UUID.randomUUID().toString());
        participant.setVersion(1);
        participant.setName("New Participant 2");
        participant.setPayloadClassName(Participant.class.getSimpleName());

        return participant;
    }
}
