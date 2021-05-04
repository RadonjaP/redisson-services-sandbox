package com.rprelevic.ta.redis.jpaprocessor.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PARTICIPANT")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OBJECT_UNIQUE_IDENTIFIER")
    private String oui;

    @Column(name = "LAST_UPDATED_ON")
    private LocalDateTime lastUpdatedOn;

}
