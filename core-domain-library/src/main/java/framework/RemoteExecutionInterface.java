package framework;


/**
 * CORE DOMAIN FRAMEWORK
 */
public interface RemoteExecutionInterface<T extends DistributedObject> {

    Boolean check(T message);

    String objectClassType();

    DistributedObject callback(T message);

}
