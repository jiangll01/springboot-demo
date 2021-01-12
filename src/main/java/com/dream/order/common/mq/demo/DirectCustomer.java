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
 * @Date: 2020/11/21 16:01
 * @Description:
 *//*

@Slf4j
@Component
public class DirectCustomer {
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(name = "direct1"),
                    key={"info","error"},
                    exchange = @Exchange(type = "direct",name="directs")
            )})
    public void receive1(String message){
        log.info("message1 = " + message);
    }

    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(name = "direct2"),
                    key={"error"},
                    exchange = @Exchange(type = "direct",name="directs")
            )})
    public void receive2(String message){
        log.info("message2 = " + message);
    }
}
*/
