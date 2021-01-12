/*
package com.dream.order.common.mq.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

*/
/**
 * @author jiangll01
 * @Date: 2020/11/22 15:34
 * @Description: 通过配置队列中消息存活时间和死信队列来实现延迟队列, 设置队列存活两分钟，
 * 当队列的消息超过队列的存活时间后，就会交给死信队列进行处理
 * <p>
 * 死信定义
 * dead-letter，死信，以下几种情况会发生死信并让死信进入死信队列：
 * <p>
 * 1.消费方调用channel.basicNack或者channel.basicReject时，并且requeue参数设置为false
 * <p>
 * 2.消息在队列中存在时间超过TTL(time-to-live)
 * <p>
 * 3.消息超过了队列允许的最大长度；
 * <p>
 * 死信队列需要在配置队列queue时，设置死信队列信息
 * <p>
 * 死信队列消费者，延迟队列不能有消费者，要不然会直接被消费者消费
 * @param message 死信消息*//*



@Component
@Slf4j
public class DelayedConsumer1 {

*/
/**
 * 死信队列消费者，延迟队列不能有消费者，要不然会直接被消费者消费
 *
 * @param message 死信消息*//*



    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "queue.dead"),
                    exchange = @Exchange(name = "exchange.delay.dead"),
                    key = {"key.delay.dead"}
            )
    })
    @RabbitHandler
    public void recevice1(String message) {
        log.info("私信队列消费"+ " " + message);
    }
}
*/
