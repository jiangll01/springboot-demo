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
 * @author jiangll01
 * @Date: 2020/11/21 16:10
 * @Description:
 *//*

@Component
@Slf4j
public class TopCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "topic1"),
                    key = {"user.*"},
                    exchange = @Exchange(type = "topic",name = "topics")
            )
    })
    public void receive1(String message){
        log.info("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "topic1"),
                    key = {"user.#"},
                    exchange = @Exchange(type = "topic",name = "topics")
            )
    })
    public void receive2(String message){
        log.info("message2 = " + message);
    }
}
*/
