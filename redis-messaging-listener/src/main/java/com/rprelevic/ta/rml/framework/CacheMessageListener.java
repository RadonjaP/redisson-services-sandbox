package com.rprelevic.ta.rml.framework;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface CacheMessageListener<T> {

    /**
     * Specify which object type is service subscribing to.
     *
     * @return - object type
     */
    Class<T> objectClassType();

    /**
     * Specify which objects of object type to select for processing.
     *
     * @return - Predicate to select objects from cache
     */
    Predicate<T> check();

    /**
     * Define how to process selected objects.
     *
     * @return - Consumer function that will be applied to selected objects
     */
    Consumer<T> callbackFunction();

}
