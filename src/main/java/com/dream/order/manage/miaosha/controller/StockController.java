package com.dream.order.manage.miaosha.controller;

import com.dream.order.common.redis.utis.StringRedisHelper;
import com.dream.order.manage.miaosha.service.OrderService;
import com.dream.order.manage.miaosha.service.UserService;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/10 10:17
 * @Description: 设计秒杀系统的主要面临的问题
 * 1、严格防止超卖：库存100件你卖了120件，等着辞职吧
 * 2、防止黑产：防止不怀好意的人群通过各种技术手段把你本该下发给群众的利益全收入了囊中。
 * 3、保证用户体验：高并发下，别网页打不开了，支付不成功了，购物车进不去了，地址改不了了。这个问题非常之大，涉及到各种技术，
 * 也不是一下子就能讲完的，甚至根本就没法讲完。
 * 4、接口限流
 * 5、隐藏秒杀接口
 */
@RestController
@Slf4j
@RequestMapping("/stock")
public class StockController {
    private OrderService orderService;

    //创建令牌桶实例
    private RateLimiter rateLimiter = RateLimiter.create(40);

    // 限时抢购

    private StringRedisHelper redisHelper;

    //控制单用户访问次数
    private UserService userService;

    @Autowired
    public StockController(OrderService orderService, StringRedisHelper redisHelper, UserService userService) {
        this.orderService = orderService;
        this.redisHelper = redisHelper;
        this.userService = userService;
    }

    /**
     * 秒杀系统要解决的问题 1、超卖问题
     * 秒杀接口，秒杀的步骤
     * 1、校验库存  查询已售的数量，跟商品秒杀数量比较
     * 2、扣除库存，库存的话，就是已售字段加1
     * 3、生成订单  通过MQ实现异步、流量削峰
     *
     * @param id 商品订单
     * @return 下单成功的订单
     */
    @GetMapping("/sale")
    public String sale(Integer id,Integer userId,String md5) {
        if (!redisHelper.isExistForValue("kill" + id)) {
            log.info("秒杀超时,活动已经结束啦!!!");
            return "秒杀超时,活动已经结束啦!!!";
        }
        //1.没有获取到令牌请求一直等待直到获取到token 令牌
        //log.info("等待的时间: "+  rateLimiter.acquire());
        //2.设置一个等待时间,如果在等待的时间内获取到了token 令牌,
        // 则处理业务,如果在等待时间内没有获取到响应token则抛弃
        if (!rateLimiter.tryAcquire(2, TimeUnit.SECONDS)) {
            log.info("当前请求被限流,直接抛弃,无法调用后续秒杀逻辑....");
            return "抢购失败!，当前秒杀活动过于火爆，请重试！！！";
        }
        //单用户限制调用频率
        boolean isBanned = userService.getUserCount(userId);
        if (isBanned) {
            log.info("购买失败,超过频率限制!");
            return "购买失败，超过频率限制!";
        }
        //创建订单
        try {
            return String.valueOf(orderService.createOrder(id,userId,md5));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //生成md5值的方法
    @RequestMapping("md5")
    public String getMd5(Integer id, Integer userId) {
        String md5;
        try {
            md5 = orderService.getMd5(id, userId);
        }catch (Exception e){
            e.printStackTrace();
            return "获取md5失败: "+e.getMessage();
        }
        return "获取md5信息为: "+md5;
    }
}
