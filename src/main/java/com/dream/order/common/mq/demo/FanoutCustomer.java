/*
package com.thread.order.common.mq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

*/
/**
 * 广播模式，消息发送到交换机，交换机把消息发到绑定的队列上
 *//*

@Slf4j
@Component
public class FanoutCustomer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout1"), //创建队列
            exchange = @Exchange(name="fanout",type = "fanout")  //绑定交换机类型
    ))
    public void receive1(String message){
        log.info("message1 = " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout2"), //创建队列
            exchange = @Exchange(name="fanout",type = "fanout")  //绑定交换机类型
    ))
    public void receive2(String message){
        log.info("message2 = " + message);
    }
}
*/
