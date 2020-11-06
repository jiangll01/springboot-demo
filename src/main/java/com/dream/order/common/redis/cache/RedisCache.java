package com.dream.order.common.redis.cache;

import com.dream.order.common.redis.utis.ApplicationContextUtils;
import com.dream.order.common.redis.utis.RedisHelper;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.security.Key;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author jiangll01
 * @Date: 2020/11/3 19:04
 * @Description: 自定义Redis缓存实现
 */
public class RedisCache implements Cache {

    private RedisHelper redisHelper;
    //当前放入缓存的mapper的namespace
    private final String id;

    public RedisCache(String id) {
        System.out.println(id);
        this.id = id;
        this.redisHelper = (RedisHelper) ApplicationContextUtils.getBean("redisHelper");
    }


    //必须存在构造方法


    //返回cache唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    //缓存放入值  redis RedisTemplate   StringRedisTemplate
    @Override
    public void putObject(Object key, Object value) {
        redisHelper.setForHash(id,getKeyToMD5(key.toString()),value);
    }

    @Override
    public Object getObject(Object key) {
        return redisHelper.getForHash(id,getKeyToMD5(key.toString()));
    }
    //注意:这个方法为mybatis保留方法 默认没有实现 后续版本可能会实现
    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空缓存~~~");
        redisHelper.delete(id);
    }

    @Override
    public int getSize() {
        return redisHelper.hashSize(id.toString()).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }


    //封装一个对key进行md5处理方法
    private String getKeyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
