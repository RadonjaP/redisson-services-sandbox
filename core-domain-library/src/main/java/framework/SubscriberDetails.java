package framework;

import lombok.Data;

import java.io.Serializable;

/**
 * CORE FRAMEWORK LIBRARY
 */
@Data
public class SubscriberDetails implements Serializable {

    private String name;

    private String subscribedClassType;

    private String activationQuery;

}
