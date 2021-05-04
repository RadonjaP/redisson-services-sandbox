package com.rprelevic.ta.redis.jpaprocessor.repository;

import com.rprelevic.ta.redis.jpaprocessor.repository.entity.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

}
