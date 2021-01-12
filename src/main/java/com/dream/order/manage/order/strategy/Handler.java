package com.dream.order.manage.order.strategy;

import com.dream.order.manage.order.bean.OrderDTO;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:38
 * @Description: 策略定义的接口，需要实现这个接口
 */
public interface Handler {

    public abstract String handle(OrderDTO orderDTO);
}
