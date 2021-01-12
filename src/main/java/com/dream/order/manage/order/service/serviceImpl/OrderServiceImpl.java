package com.dream.order.manage.order.service.serviceImpl;

import com.dream.order.manage.order.bean.OrderDTO;
import com.dream.order.manage.order.service.OrederService;
import com.dream.order.manage.order.strategy.Handler;
import com.dream.order.manage.order.strategy.HandlerContext;
import com.dream.order.manage.order.strategy.enumContext.HandlerContextEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangll01
 * @Date: 2020/11/18 18:38
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrederService {

    private HandlerContext handlerContext;

    private HandlerContextEnum handlerContextEnum;

    @Autowired
    public OrderServiceImpl(HandlerContext handlerContext, HandlerContextEnum handlerContextEnum) {
        this.handlerContext = handlerContext;
        this.handlerContextEnum = handlerContextEnum;
    }

    @Override
    public String handle(OrderDTO orderDTO) {
        Handler instance = handlerContext.getInstance(orderDTO.getType());
        return instance.handle(orderDTO);
    }


    /**
     * 通过枚举方式进行策略模式
     *
     * @param orderDTO
     * @return
     */
    public String handle1(OrderDTO orderDTO) {
        Handler instance = handlerContext.getInstance(orderDTO.getType());
        return instance.handle(orderDTO);
    }
}
