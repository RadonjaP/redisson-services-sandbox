package com.rprelevic.ta.rml.impl;

import com.rprelevic.ta.rml.framework.CacheMessageListener;
import com.rprelevic.ta.rml.impl.model.Participant;
import lombok.extern.slf4j.Slf4j;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
@Component
public class ParticipantCacheListener implements CacheMessageListener<Participant> {

    @Override
    public Class<Participant> objectClassType() {
        return Participant.class;
    }

    @Override
    public Predicate<Participant> check() {
        return (participant ->
                !StringUtils.isNullOrEmpty(participant.getName()));
    }

    @Override
    public Consumer<Participant> callbackFunction() {
        return (participant -> {
            log.info("callbackFunction() ... participantName -> {}", participant);
        });
    }

}
