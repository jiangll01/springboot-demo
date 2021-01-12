package com.dream.order.common.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiangll01
 * @Date: 2020/10/20 14:41
 * @Description: 1、setIfAbsent(lockKey,value,expireTime, timeUnit) 可以保证加锁和时间是原子性操作
 * 2、注意redis的版本要2.1之上才有这个方法
 * 问题：单节点redis情况下出现的问题，锁问题
 * 1、首先是加锁和时间没办法保证原子性 -> redis已经给解决了
 * 2、如果一个线程A执行代码的时间过长，导致可能出现A锁已经失效，另一个线程B加锁后，A线程释放了B锁 -> value值可以设置为
 * 线程id或者我们通过uuid来实现，进行释放锁时进行对比是否释放了自己的锁
 * 虽然解决了没办法删除别人的锁了，但是这个锁还是到时间就释放了，还是出现问题，可以通过守护线程的方式进行时间检测，不断地加时间
 * 解决：可以通过导入Redis客户端Redisson
 * 可以通过时间判断来进行加时间？释放锁
 */
@Component
public class RedisDistributedLock {

    StringRedisTemplate stringRedisTemplate;
    DelayQueueManager delayQueueManager;
    //并发情况下，id实现自增
    private AtomicInteger id;
    private String value;

    @Autowired
    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate, DelayQueueManager delayQueueManager) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.delayQueueManager = delayQueueManager;
    }


    public boolean get(String lockKey, String value, long expireTime, TimeUnit timeUnit) {
        //开启延时队列进行判断，如果代码执行的时间过长，要维护这把锁
        this.value = UUID.randomUUID().toString();
        Message message = new Message(this.id.getAndIncrement(), value, expireTime / 2, timeUnit);
        delayQueueManager.put(message);
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, value, expireTime, timeUnit));
    }

    public boolean release(String lockKey, String value) {
        if (value.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
            return Boolean.TRUE.equals(stringRedisTemplate.delete(lockKey));
        }
        return false;
    }
}
