package com.dream.order.manage.factory;

import org.springframework.stereotype.Component;

/**
 * @author jiangll01
 * @Date: 2021/1/7 14:28
 * @Description:
 */
@Component
public class Tsl implements Car {
    @Override
    public void run() {
        System.out.println("特斯拉跑的飞快，但是他没五零猛");
    }

    @Override
    public void stop() {

    }

    @Override
    public void take() {

    }
}
