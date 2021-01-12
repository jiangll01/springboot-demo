package com.dream.order.manage.order.service;

import com.dream.order.manage.order.bean.OrderDTO;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:32
 * @Description: 利用策略模式，方面后期的维护拓展
 */
public interface OrederService {

    /**
     * 根据订单的不同类型做出不同的处理
     *
     * @param orderDTO 订单实体
     * @return 字符串
     */
    String handle(OrderDTO orderDTO);
}
