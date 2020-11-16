package com.dream.order.manage.miaosha.service;

import com.dream.order.common.redis.utis.RedisHelper;
import com.dream.order.common.redis.utis.StringRedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/16 11:31
 * @Description: 用户
 */
@Slf4j
@Service
public class UserService {

    private RedisHelper redisHelper;

    private static final int COUT = 10;

    @Autowired
    public UserService(RedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    public boolean getUserCount(Integer userId) {
        String key = "LIMIT" + "_" + userId;
        //判断有key吗，如果没key的话，创建key初始化为1
        if (redisHelper.isExistForValue(key)) {
            redisHelper.inCr(key, 1);
        } else {
            redisHelper.setForValue(key, 1, 3600, TimeUnit.SECONDS);
        }
        int count = (int) redisHelper.getForValue(key);
        log.info("用户截至该次的访问次数为: [{}]", count);
        //false代表没有超过 true代表超过
        return count > COUT;
    }
}
