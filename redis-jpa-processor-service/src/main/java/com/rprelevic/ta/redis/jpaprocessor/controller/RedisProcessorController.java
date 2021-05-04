package com.rprelevic.ta.redis.jpaprocessor.controller;

import com.rprelevic.ta.redis.jpaprocessor.controller.model.ParticipantRequestModel;
import com.rprelevic.ta.redis.jpaprocessor.controller.model.ParticipantResponseModel;
import com.rprelevic.ta.redis.jpaprocessor.repository.entity.Participant;
import com.rprelevic.ta.redis.jpaprocessor.service.RedisProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping(value = "/participant")
public class RedisProcessorController {

    @Autowired
    private RedisProcessorService redisProcessorService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createParticipant(@RequestBody ParticipantRequestModel requestModel) {
        log.info("Creating entity...");
        int result = Math.toIntExact(redisProcessorService.createParticipant(requestModel).getId());
        return new ResponseEntity<>("Entity saved with ID " + result, HttpStatus.OK);
    }


    @GetMapping(value = "/get")
    public ResponseEntity<ParticipantResponseModel> getForId(@RequestParam("id") String id) {
        log.info("getting entity for id: {} ...", id);
        Participant response = redisProcessorService.getParticipant(id).orElse(new Participant());
        return new ResponseEntity<>(ParticipantResponseModel.builder()
                .id(response.getId())
                .name(response.getName())
                .lastUpdatedOn(response.getLastUpdatedOn())
                .build(), HttpStatus.OK);
    }

}
