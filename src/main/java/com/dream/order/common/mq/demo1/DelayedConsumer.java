/*
package com.thread.order.common.mq.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

*/
/**
 * @author jiangll01
 * @Date: 2020/11/21 20:16
 * @Description: 外卖软件中的超时未接单、超时未支付、超时未上门、
 * 超时自动确认等功能非常适合通过延时交换机（Delayed message exchange）实现。
 * 延迟交换机有2种实现方法，一种是用死信交换机，一种是安装插件（推荐），这里写的插件方式实现。
 *//*

@Slf4j
@Component
public class DelayedConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "delayed"),
                    exchange = @Exchange(name = "delayed",delayed = "true"),
                    key = {"delayed"}
            )
    })
    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        log.info("myQueue1:" + new String(message));


    }
}
*/
