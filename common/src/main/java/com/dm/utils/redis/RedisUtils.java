package com.dm.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("redisTemplate")
    private void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    public static void flushDB() {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return "OK";
            }
        });
    }

    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public static boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    public static Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public static void set(String key, Object value) {
        //System.out.println(redisTemplate);
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, Object value, Long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    public static Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * operate String
     * */
    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, String value, Long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    public static String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    /**
     * operate list
     * */
    public static Long setList(String key, List<?> list) {
        return redisTemplate.opsForList().leftPushAll(key, list);
    }

    public static List<?> getList(String key) {
        return (List)redisTemplate.opsForList().range(key, 0, -1);
    }

    /**redis transaction*/
    public static void watch(String key) {
        redisTemplate.watch(key);
    }

    public static void watch(Collection<String> keys) {
        redisTemplate.watch(keys);
    }

    public static List<Object> exec() {
        return redisTemplate.exec();
    }

    public static void multi() {
        redisTemplate.multi();
    }

    /**终止事务，不执行*/
    public static void discard() {
        redisTemplate.discard();
    }
}