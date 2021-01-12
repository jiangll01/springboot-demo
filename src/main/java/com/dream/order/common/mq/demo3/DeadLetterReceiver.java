/*
package com.thread.order.common.mq.demo3;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

*/
/**
 * @author jiangll01
 * @Date: 2020/11/21 23:11
 * @Description: 消费失败也有ack机制，和生成者ack不同。我们根据处理结果返回ack（确认收到）、nack（确认未收到）、reject（拒绝）（需开启手动ack模式）。
 * mq收到是ack的话会把消息从mq中剔除，不剔除的话mq会不断重试。
 * <p>
 * basicNack(long deliveryTag, boolean multiple, boolean requeue)
 * deliveryTag: 每条消息在mq内部的id,
 * multiple: 是否批量(true：将一次性拒绝所有小于deliveryTag的消息)；
 * requeue: 是否重新入队
 * 给该队列配置死信交换机，Nack、Reject、消息过期、队列超载都会自动投递到死信交换机
 * <p>
 * <p>
 * 该队列所有Nack、Reject、消息过期、队列超载的消息都会自动投递到死信交换机
 * <p>
 * 在消息消费时，要求消息体中必须要有一个bizId（对于同一业务全局唯一，如支付ID、订单ID、帖子ID等）作为去重和幂等的依据，
 * 避免同一条消息被重复消费（幂等可以用redis实现，在消费者中把bizid setNX到redis中，
 * 若setNX返回false，表示重复消费，则直接return不做任何消费逻辑处理）。
 * <p>
 * 死信交换机
 *//*

@Component
@Slf4j
public class DeadLetterReceiver {
    */
/**
 * basicNack(long deliveryTag, boolean multiple, boolean requeue)
 * deliveryTag: 每条消息在mq内部的id,
 * multiple: 是否批量(true：将一次性拒绝所有小于deliveryTag的消息)；
 * requeue: 是否重新入队
 * 给该队列配置死信交换机，Nack、Reject、消息过期、队列超载都会自动投递到死信交换机
 *//*

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "myQueue6",
                            arguments = {
                                    @Argument(name = "x-dead-letter-exchange", value = "myExchange1_dlx"),
                                    @Argument(name = "x-dead-letter-routing-key", value = "routingKey4_dlx")}),
                    exchange = @Exchange(value = "myExchange1"),
                    key = "routingKey4"
            ))

    */
/**
 *
 * 该队列所有Nack、Reject、消息过期、队列超载的消息都会自动投递到死信交换机
 *
 * 在消息消费时，要求消息体中必须要有一个bizId（对于同一业务全局唯一，如支付ID、订单ID、帖子ID等）作为去重和幂等的依据，
 * 避免同一条消息被重复消费（幂等可以用redis实现，在消费者中把bizid setNX到redis中，
 * 若setNX返回false，表示重复消费，则直接return不做任何消费逻辑处理）。
 *//*

    public void process7(@Payload Message message, Channel channel) throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            // 抛出异常的话进入私信队列
            log.info("逻辑执行异常，消息投递到私信队列");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }

    */
/**
 * 死信交换机
 *//*

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "myQueue9"),
                    exchange = @Exchange(value = "myExchange1_dlx"),
                    key = "routingKey4_dlx"
            ))
    public void process9( @Payload Message message,Channel channel) throws IOException {
        log.info("消息进行死信交换机");
        log.info(new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
*/
