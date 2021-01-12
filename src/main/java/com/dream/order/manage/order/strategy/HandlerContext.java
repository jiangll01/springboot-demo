package com.dream.order.manage.order.strategy;

import com.dream.order.common.redis.utis.ApplicationContextUtils;

import java.util.Map;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:50
 * @Description: 策略模式上下文
 */

public class HandlerContext {

    private Map<String, Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public Handler getInstance(String type) {
        return (Handler) ApplicationContextUtils.getBean(handlerMap.get(type));
    }
}
