package com.dream.order.manage.order.strategy.impl;

import com.dream.order.manage.order.annotation.HandlerType;
import com.dream.order.manage.order.bean.OrderDTO;
import com.dream.order.manage.order.strategy.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:42
 * @Description:
 */
@Component
@HandlerType("1")
@Slf4j
public class NormalHandler implements Handler {
    @Override
    public String handle(OrderDTO orderDTO) {
        log.info("处理正常订单");
        return "处理正常订单";
    }
}
