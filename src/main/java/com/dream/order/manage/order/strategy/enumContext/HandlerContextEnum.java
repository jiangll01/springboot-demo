package com.dream.order.manage.order.strategy.enumContext;

import com.dream.order.common.redis.utis.ApplicationContextUtils;
import com.dream.order.manage.order.strategy.Handler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:50
 * @Description: 策略模式上下文，通过枚举进行存储实现类
 */

@Component
public class HandlerContextEnum {
    public Handler getInstance(String type) {
        return (Handler) Arrays.stream(HandlerEnum.values())
                .filter(value -> value.getId().equals(type))
                .map(e -> ApplicationContextUtils.getBean(e.getBeanName()))
                .collect(Collectors.toList());
    }
}
