/*
package com.thread.order.common.mq.demo1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

*/
/**
 * @author jiangll01
 * @Date: 2020/11/21 19:48
 * @Description: 开启多线程消费, 创建了十个消费者去连接了rabbitmq进行消费
 *//*

@Slf4j
@Component
public class MultithreadingConsumer {

    @SneakyThrows
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "multiThreadingKey"),
                    exchange = @Exchange("directs"),
                    key = {"multiThreadingKey"}
            )
    },concurrency = "10" // concurrency：并发数量
    )
    @RabbitHandler
    public void receive(String message) {
        log.info("开启多线程消费");
        log.info("myQueue1:" + new String(message));
        Thread.sleep(5000);


    }
}
*/
