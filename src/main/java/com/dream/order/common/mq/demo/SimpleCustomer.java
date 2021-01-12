/*
package com.thread.order.common.mq.demo;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

*/
/**
 * @author jiangll01
 * @Date: 2020/11/17 17:34
 * @Description: 一个生产者对应一个队列和一个消费者，没有交换机的存在
 * <p>
 * 消息（message）：生产者参数做为消息发给mq，消费者把消息当做参数执行具体逻辑。
 * 默认是持久化（durable）的，重启之后依旧在。
 * 监听
 * 参数：queuesToDeclare 定义队列
 * ackMode  应答模式
 * 队列  Queue ： 默认是持久化（durable）的，重启之后依旧在
 * 参数1: durable 是否持久化   持久化时，rabbitmq 挂了，队列和消息还可以恢复
 * 参数2 exclusive :是否独占队列
 * 参数3 autoDelete:是否自动删除   当autoDelete = true 时，服务器挂了话，队列中的数据会丢失
 * 参数4:其他属性
 * <p>
 * 消费者，点对点模式，没有交换机
 * @param message 监听的消息 {@link Message}
 * @param channel 连接通道 {@link Channel}
 *//*

@Component
@Slf4j
public class SimpleCustomer {
    @SneakyThrows
    @RabbitHandler//表示回调
    @RabbitListener(queuesToDeclare = @Queue(name = "simple",durable = "true",exclusive = "false",
            autoDelete = "false"))

    */
/**
 * 消费者，点对点模式，没有交换机
 * @param message 监听的消息 {@link Message}
 * @param channel 连接通道 {@link Channel}
 *//*

    public void receive(Message message, Channel channel) throws IOException {
        //Thread.sleep(3000);
        log.info("message = " + new String(message.getBody()));
        // 采用手动应答模式, 手动确认应答更为安全稳定
       channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
*/
