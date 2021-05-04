package com.rprelevic.ta.redis.jpaprocessor.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantRequestModel {

    private String name;

    /**
     * Object Unique Identifier
     */
    private String oui;

}
