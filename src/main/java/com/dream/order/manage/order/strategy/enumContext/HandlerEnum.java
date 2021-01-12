package com.dream.order.manage.order.strategy.enumContext;

/**
 * @author jiangll01
 * @Date: 2020/11/19 10:06
 * @Description: 枚举实现策略模式，可以取代if else
 */

public enum HandlerEnum {
    GroupHandler("2", "GroupHandler"), NormalHandler("1", "NormalHandler"),
    PromotionHandler("3", "PromotionHandler");
    private String id;
    private String beanName;

    HandlerEnum(String id, String beanName) {
        this.id = id;
        this.beanName = beanName;
    }

    public String getId() {
        return id;
    }

    public String getBeanName() {
        return beanName;
    }
}
