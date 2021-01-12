/*
package com.thread.order.common.mq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

*/
/**
 * @author jiangll01
 * @Date: 2020/11/21 15:25
 * @Description: 工作队列
 *//*

@Component
@Slf4j
public class WorkCustomer {

    @RabbitListener(queuesToDeclare = {@Queue(name = "work")})
    @RabbitHandler
    public void receive(String message) throws IOException {
        log.info("消费者1"+ message);
    }

    @RabbitListener(queuesToDeclare = {@Queue(name = "work")})
    @RabbitHandler
    public void receive1(String message) throws IOException {
        log.info("消费者2"+ message);
    }

}
*/
