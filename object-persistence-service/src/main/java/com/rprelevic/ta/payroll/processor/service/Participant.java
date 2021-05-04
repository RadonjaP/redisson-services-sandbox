package com.rprelevic.ta.payroll.processor.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import framework.DistributedObject;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "DistributedObject")
public class Participant extends DistributedObject {

    private String name;

}
