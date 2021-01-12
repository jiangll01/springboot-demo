package com.dream.order.manage.factory;

import org.springframework.stereotype.Component;

/**
 * @author jiangll01
 * @Date: 2021/1/7 14:27
 * @Description:
 */
@Component
public class WuLing implements Car {
    @Override
    public void run() {
        System.out.println("五零跑的飞快");
    }

    @Override
    public void stop() {

    }

    @Override
    public void take() {

    }
}
