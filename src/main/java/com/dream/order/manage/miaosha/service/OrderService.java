package com.dream.order.manage.miaosha.service;

import com.dream.order.common.redis.utis.StringRedisHelper;
import com.dream.order.manage.miaosha.entity.Order;
import com.dream.order.manage.miaosha.entity.Stock;
import com.dream.order.manage.miaosha.mapper.OrderMapper;
import com.dream.order.manage.miaosha.mapper.StockMapper;
import com.dream.order.manage.miaosha.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/10 10:16
 * @Description: 并发情况出现数据不安全
 * 1、解决方法：单节点部署的话，可以考虑用锁，synchronize 但是效率太低 很影响
 * 2、多节点部署的话，锁失效，考虑使用乐观锁，利用mysql的写独占，通过加版本号
 */

@Service
public class OrderService {

    private StockMapper stockMapper;
    private OrderMapper orderMapper;
    private UserMapper userMapper;
    private StringRedisHelper stringRedisHelper;

    @Autowired
    public OrderService(StockMapper stockMapper, OrderMapper orderMapper, UserMapper userMapper, StringRedisHelper stringRedisHelper) {
        this.stockMapper = stockMapper;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
        this.stringRedisHelper = stringRedisHelper;
    }

    public Integer createOrder(Integer id, Integer userId, String md5) {
        //先验证签名
        String key = "KEY_" + userId + "_" + id;
        if (stringRedisHelper.getForValue(key) == null) {
            throw new RuntimeException("没有携带验证签名,请求不合法!");
        }
        if (!stringRedisHelper.getForValue(key).equals(md5)) {
            throw new RuntimeException("当前请求数据不合法,请稍后再试!");
        }
        //同步代码块 效率低下，不合适多节点部署
        //1、校验库存
        Stock stock = checkStock(id);
        //2、扣除库存
        //在sql层面完成销量的+1  和 版本号的+  并且根据商品id和版本号同时查询更新的商品
        //更新成功的话去执行创建订单
        updateSale(stock);
        //3、下订单
        return createOrder(stock);
    }

    /**
     * 校验库存
     *
     * @param id 商品id
     * @return
     */
    private Stock checkStock(Integer id) {
        //1、校验库存
        Stock stock = stockMapper.checkStock(id);
        //2、扣除库存
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足！！！");
        }
        return stock;
    }

    //扣除库存
    private void updateSale(Stock stock) {
        //在sql层面完成销量的+1  和 版本号的+  并且根据商品id和版本号同时查询更新的商品
        int updateSale = stockMapper.updateSale(stock);
        if (updateSale == 0) {
            throw new RuntimeException("抢购失败，请重试！！");
        }
    }

    /**
     * 创建订单
     *
     * @param stock
     * @return
     */
    private Integer createOrder(Stock stock) {
        Order order = new Order().setSid(stock.getId()).setName(stock.getName()).setDate(new Date());
        orderMapper.createOrder(order);
        return order.getId();
    }

    public String getMd5(Integer id, Integer userId) {
        if (ObjectUtils.isEmpty(userMapper.findById(userId))) {
            throw new RuntimeException("用户信息不存在!");
        }
        if (ObjectUtils.isEmpty(stockMapper.checkStock(id))) {
            throw new RuntimeException("商品信息不合法!");
        }
        //生成hashkey
        String key = "KEY_" + userId + "_" + id;
        //生成md5//这里!QS#是一个盐 随机生成
        String value = DigestUtils.md5DigestAsHex((userId + id + "!Q*jS#").getBytes());
        stringRedisHelper.setForValue(key, value, 3600, TimeUnit.SECONDS);
        return value;
    }

    @Transactional
    public void createOrder1() {

        try {
            Order order = new Order().setId(1).setName("1").setSid(1);
            heh(order);
        } catch (Exception e) {
            System.out.println("异常了");
            throw new RuntimeException("aa");

        }

    }

    private void heh(Order order) {
        orderMapper.createOrder(order);
        int i = 1 / 0;
        String str = "真的是呵呵打";
    }
}

