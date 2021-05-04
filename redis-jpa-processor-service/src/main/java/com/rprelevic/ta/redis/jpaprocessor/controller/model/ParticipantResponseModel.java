package com.rprelevic.ta.redis.jpaprocessor.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponseModel {

    private Long id;

    private String name;

    private LocalDateTime lastUpdatedOn;

}
