package com.rprelevic.ta.rml.framework.redis;

import com.rprelevic.ta.rml.framework.CacheMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisCacheMessageListener<T> implements MessageListener {

    @Autowired
    private CacheMessageListener<T> messageListener;

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("OnMessage activated...");
        String key = new String(message.getBody());
        if (!messageObjectMatched(key, messageListener.objectClassType())) {
            // Message doesn't belong to this receiver
            return;
        }

        // Get value from Redis
        T object = (T) redisTemplate.opsForValue().get(key);

        if (messageListener.check().test(object)) {
            messageListener.callbackFunction().accept(object);
        }
    }

    public Class<T> getObjectClassType() {
        return messageListener.objectClassType();
    }

    private boolean messageObjectMatched(String key, Class<T> objectKey) {
        return key.toLowerCase().contains(objectKey.getSimpleName().toLowerCase());
    }

}
