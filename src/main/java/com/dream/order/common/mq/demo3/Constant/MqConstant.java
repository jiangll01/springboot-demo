package com.dream.order.common.mq.demo3.Constant;

/**
 * @author jiangll01
 * @Date: 2020/11/23 17:09
 * @Description: 消息配置
 */
public class MqConstant {

    /**
     * 开通资源后，发送给订单中心的消息队列
     */
    public static final String EXCHANGE_CONSOLE_ORDER = "exchange.create.order";

    // 开通资源后，通知订单侧绑定的RoutingKey
    public static final String NOTIFY_ORDER_ROUTINGKEY = "key.create.order";
}
