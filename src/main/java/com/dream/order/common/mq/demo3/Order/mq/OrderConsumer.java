package com.dream.order.common.mq.demo3.Order.mq;

import com.dream.order.common.mq.demo3.Exception.MyException;
import com.dream.order.common.mq.demo3.Instance.InstanceProducer;
import com.dream.order.common.mq.demo3.bean.Instance;
import com.dream.order.common.redis.utis.StringRedisHelper;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author jiangll01
 * @Date: 2020/11/23 15:10
 * @Description:
 */
@Component
@Slf4j
public class OrderConsumer {
    //统计重试次数
    private final StringRedisHelper stringRedisHelper;

    private final InstanceProducer instanceProducer;

    @Autowired
    public OrderConsumer(StringRedisHelper stringRedisHelper, InstanceProducer instanceProducer) {
        this.stringRedisHelper = stringRedisHelper;
        this.instanceProducer = instanceProducer;
    }

    @SneakyThrows
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

    /**
     * 网络抖动、消费者代码异常、数据异常。
     * 答:   在消费者catch块里返回nack，返回nack之前还要做计数。
     *       达到规定的次数后将消息存到缓存并返回ack
     * （因为消费者代码异常、数据异常导致的消费失败重试多少次都成功不了，不处理的话会死循环的）。
     * 模拟消费者代码异常，这种情况必须在catch块设置重试次数，防止死循环
     * catch块中重试可用redis的自增来做计数器，从而控制重试次数（分布式集群）
     *
     * 在消息消费时，要求消息体中必须要有一个bizId（对于同一业务全局唯一，如支付ID、订单ID、帖子ID等）作为去重和幂等的依据，
     * 避免同一条消息被重复消费（幂等可以用redis实现，在消费者中把bizid setNX到redis中，
     * 若setNX返回false，表示重复消费，则直接return不做任何消费逻辑处理）。
     */
    // 1、请求三方接口导致的网络问题可以通过重复消费，需要记录重试的次数，避免死循环
    // 2、消费者代码异常的话，如何重试都没办法解决问题，需要处理，需要给生产者发消息，取消这个消息，重新发送
    // 3、针对幂等性，重复消费问题，需要全局唯一值来进行处理
    @Transactional(rollbackFor = MyException.class)
    public void process7(@Payload Message message, Channel channel, CorrelationData correlationData) {
        try {
            //处理消息幂等性，给生产者回用消息，保证一致性
            if (Boolean.FALSE.equals(stringRedisHelper.setIfAbsentForValue(correlationData.getId(), message.toString()))) {
                log.info("订单id:{}出现幂等性问题了", correlationData.getId());
                return;
            }
            // todo 处理业务逻辑
            int i = 1 / 0;

            //如果没有异常的话，把订单的唯一值、生成的实例关联起来，返回给生产者处理
            Instance instance = new Instance().setInstanceId(UUID.randomUUID().toString());
            instanceProducer.produce(instance, correlationData);
        } catch (RuntimeException e) {
            log.error("Create an instance exception, exception information{}", e.getMessage());
            log.info("exception message", e);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}

