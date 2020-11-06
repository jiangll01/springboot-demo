package com.dream.order.common.redis.utis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/10/19 11:29
 * @Description: Redis缓存封装类, 数据类型比较多，主要
 * 封装了操作value为对象的
 */
@SuppressWarnings("all")
@Component
public class StringRedisHelper {

    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    public StringRedisHelper(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 操作String类型
     */

    /**
     * 根据key删除缓存
     *
     * @param key 键
     */
    public Boolean removeForValue(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 获取指定key的失效时间
     *
     * @param key 键
     * @return 返回失效时间（单位：秒）
     */
    public Long getExpireForValue(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断redis中是否存在指定的key
     *
     * @param key 键
     * @return true:表示存在；false：不存在
     */
    public boolean isExistForValue(String key) {
        Assert.notNull(key, "non null key required");
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 字符串型K-V  设置一个key/value
     *
     * @param key
     * @param value
     */

    public void setForValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 字符串型K-V  根据key获得对应的value
     *
     * @param key
     */
    public String getForValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 一次设置多个key value
     *
     * @param map
     */
    public void multiSetForValue(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 一次获得多个key的value
     *
     * @param key
     * @return
     */
    public List<String> multiGetForValue(List<String> key) {
        return stringRedisTemplate.opsForValue().multiGet(key);
    }

    /**
     * 获得原始key的值，同时设置新值
     *
     * @param key
     * @param changeObject
     * @return
     */
    public String getForValue(String key, String changeObject) {
        return stringRedisTemplate.opsForValue().getAndSet(key, changeObject);
    }

    /**
     * 获得对应key存储value的长度
     *
     * @param key
     * @return
     */
    public Long getStrLen(String key) {
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * 设置一个key存活的有效期（秒）
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */

    public void setForValue(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 如果缓存中存在该key，则存储失败，否则成功
     *
     * @param key   键
     * @param value 值
     * @return 塞值结果 true成功；false失败
     */
    public Boolean setIfAbsentForValue(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 根据提供的数据进行加法操作
     *
     * @param key
     * @param value
     */
    public void Incr(String key, long value) {
        stringRedisTemplate.opsForValue().increment(key, value);
    }

    /**
     * 根据提供的数据进行加法操作
     *
     * @param key
     * @param value
     */
    public void Incr(String key, double value) {
        stringRedisTemplate.opsForValue().increment(key, value);
    }

    /**
     *  Redis的List数据结构  只简单的操作元素的添加、取值、遍历
     */

    /**
     * 将指定的值插入存储在键的列表的头部
     * 【如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）】
     *
     * @param key   键
     * @param value 值
     * @return 返回的结果为推送操作后的列表的长度
     */

    public Long leftPushForList(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 批量把一个数组插入到列表中
     * 【如果键不存在，则在执行推送操作之前将其创建为空列表】
     *
     * @param key    键
     * @param values 数组值
     * @return 返回的结果为推送操作后的列表的长度
     */
    public Long leftPushAllForList(String key, String[] values) {
        return stringRedisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 批量把一个List集合插入到列表中
     * 【如果键不存在，则在执行推送操作之前将其创建为空列表】
     *
     * @param key       键
     * @param valueList 集合列表值
     * @return 返回的结果为推送操作后的列表的长度
     */
    public Long leftPushAllForList(String key, List<String> valueList) {
        return stringRedisTemplate.opsForList().leftPushAll(key, valueList);
    }

    /**
     * 将指定的值插入存储在键的列表的尾部
     * 【如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）】
     *
     * @param key   键
     * @param value 值
     * @return 返回的结果为推送操作后的列表的长度
     */
    public Long rightPushForList(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * * 批量把一个数组插入到列表尾部
     * 【如果键不存在，则在执行推送操作之前将其创建为空列表】
     *
     * @param values 数组值
     * @return 返回的结果为推送操作后的列表的长度
     */
    @SuppressWarnings("ALL")
    public Long rightPushAllForList(String key, String[] values) {
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 批量把一个List集合插入到列表尾部
     * 【如果键不存在，则在执行推送操作之前将其创建为空列表】
     *
     * @param key       键
     * @param valueList 集合列表值
     * @return 返回的结果为推送操作后的列表的长度
     */
    public Long rightPushAllForList(String key, List<String> valueList) {
        return stringRedisTemplate.opsForList().rightPushAll(key, valueList);
    }

    /**
     * @param key   键
     * @param start 索引开始位置 0 是从列表的第一个位置开始
     * @param end   索引的结束位置  -1表示到最后一位
     * @return
     */
    public List<String> getForList(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取指定下标的list中存储的值
     *
     * @param key   键
     * @param index 下标
     * @return 返回String值
     */
    public String indexForlist(String key, long index) {
        Object o = stringRedisTemplate.opsForList().index(key, index);
        if (null != o) {
            return o.toString();
        }
        return null;
    }
}

