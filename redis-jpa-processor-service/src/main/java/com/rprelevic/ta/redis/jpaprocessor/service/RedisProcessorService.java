package com.rprelevic.ta.redis.jpaprocessor.service;

import com.rprelevic.ta.redis.jpaprocessor.controller.model.ParticipantRequestModel;
import com.rprelevic.ta.redis.jpaprocessor.repository.ParticipantRepository;
import com.rprelevic.ta.redis.jpaprocessor.repository.entity.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class RedisProcessorService {

    @Autowired
    private ParticipantRepository participantRepository;

    @CachePut(value = "participant", key = "#request.hashCode()") //TODO: change to OUI */
    public Participant createParticipant(final ParticipantRequestModel request) {
        Participant participant = Participant.builder()
                .name(request.getName())
                .oui(request.getOui())
                .lastUpdatedOn(LocalDateTime.now())
                .build();
        return participantRepository.save(participant);
    }

    public Optional<Participant> getParticipant(final String id) {
        Long longId = Long.parseLong(id);
        return participantRepository.findById(longId);
    }

}
