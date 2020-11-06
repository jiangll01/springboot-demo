package com.dream.order.common.redis.utis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jiangll01
 * @Date: 2020/10/21 10:04
 * @Description:
 */

@Component
public class RedisHelper {
    /**
     * 操作Object类型
     */
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public RedisHelper(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setForValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getForValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void leftPushAllForList(String key, Object... value) {
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    public List<Object> getAllForList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public void setForHash(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void setAllForHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Object getForHash(String key, String hashKey) {
        return  redisTemplate.opsForHash().get(key, hashKey);
    }

    public Map<Object, Object> getAllForHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long hashSize(String key) {
       return redisTemplate.opsForHash().size(key);
    }

    public void setForSet(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void setForSet(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set<Object> getAllForSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void setForZset(String key, Object object, double score) {
        redisTemplate.opsForZSet().add(key, object, score);
    }

    public void setForZset(String key, Set<ZSetOperations.TypedTuple<Object>> value) {
        redisTemplate.opsForZSet().add(key, value);
    }
    public void getForZset(String key) {
        redisTemplate.opsForZSet().range(key,1,10);
    }
}
