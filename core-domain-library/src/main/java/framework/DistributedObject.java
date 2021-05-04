package framework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

import java.io.Serializable;


/**
 * CORE FRAMEWORK LIBRARY
 */
@Data
@REntity
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "DistributedObject")
public class DistributedObject implements Serializable {

    //@RId
    private String id;

    //@RIndex
    private Integer version;

    //@RIndex
    private String payloadClassName;

}
