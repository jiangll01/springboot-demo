package com.dream.order.common.mq.demo3.Order.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author jiangll01
 * @Date: 2020/11/21 21:24
 * @Description: 消息发送失败情况：
 * 1、网络抖动导致生产者和mq之间的连接中断，导致消息都没发。
 * 答：rabbitmq有自动重连机制，叫retry。具体到rabbitTemplate中叫retryTemplate，
 * 可以通过设置retryTemplate来设置重连次数。
 * 1.1、到了重连次数了，还是没连上怎么办呢？造成这种情况通常是服务器宕机等环境问题，这时候会报AmqpException，           我们可以捕获这个异常，然后把消息存入缓存中。等环境正常后，做消息补发。
 * <p>
 * 2、消息发了但是mq没收到，或者mq收到了但是进入到交换机之前（如果开启了消息持久化，那
 * 则是持久化之前。交换机、队列、消息默认都是持久化的）消息丢了。
 * <p>
 * 答：rabbitmq有confirm机制，即mq收到消息后会发送一个叫ack的标识给生产者，ack为true表示收到了，a
 * ck为false表示没收到或丢了。rabbitTemplate中有confirmCallback，在这个callback里把ack为false的消息存到缓存，
 * 用另外线程重发。
 * <p>
 * 3、消息到交换机了，但是找不到对应的queue。
 * <p>
 * 答：rabbitmq有return机制，在rabbitTemplate中有returnCallback。找不到queue的消息都会进入到这个callback，
 * 在这个callback里把消息存到缓存，用另外线程重发。
 * 消费失败情况：
 * <p>
 * 消费失败也有ack机制，和生成者ack不同。我们根据处理结果返回ack（确认收到）、nack（确认未收到）、reject（拒绝）（需开启手动ack模式）。
 * mq收到是ack的话会把消息从mq中剔除，不剔除的话mq会不断重试。
 * <p>
 * 1、网络抖动、消费者代码异常、数据异常。
 * 答：在消费者catch块里返回nack，返回nack之前还要做计数。达到规定的次数后将消息存到缓存并返回ack（因为消费者代码异常、
 * 数据异常导致的消费失败重试多少次都成功不了，不处理的话会死循环的）。
 */
@RestController
@Slf4j
@Scope("prototype")
public class OrderProducer {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderProducer(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setMandatory(true);
        this.rabbitTemplate = rabbitTemplate;

    }

    @GetMapping(value = "/mq")
    public String send() {
        String msg = "创建实例信息";
        // 构造包含消息的唯一id的对象，id 必须在该 channel 中始终唯一
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        // todo 先将 message 入库，在将 message 的数据库ID 、message的消息id message的初始状态(发送中)等信息入库
        // 完成 数据落库，消息状态打标后，就可以安心发送 message
        try {
            // 确认是否发到交换机，若没有则存缓存，用另外的线程重发，直接在里面用rabbitTemplate重发会抛出循环依赖错误
            rabbitTemplate.setConfirmCallback((correlationData1, ack, cause) -> {
                log.info("发送到交换机了吗");
                if (!ack) {
                    log.info("消息没发送到交换机，启用回调方法存入缓存，异步发送");
                    // todo 操作数据库，将 correlationId 这条消息状态改为投递失败
                    // 存缓存操作
                    log.info(msg + "发送失败:" + cause);
                }
                //两种处理方式,另一种
                if (ack) {
                    log.info("消息投递成功,ID为: {}", correlationData.getId());
                    // todo 操作数据库，将 correlationId 这条消息状态改为投递成功
                    return;
                }
                log.error("消息投递失败,ID为: {},错误信息: {}", correlationData.getId(), cause);
                // todo 操作数据库，将 correlationId 这条消息状态改为投递失败
            });
            // 确认是否发到队列，若没有则存缓存，然后检查exchange, routingKey配置，之后重发
            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                // 存缓存操作
                // todo 操作数据库，将 correlationId 这条消息状态改为投递失败
                log.info(new String(message.getBody()) + "找不到队列，exchange为" + exchange + ",routingKey为" + routingKey);
            });

            rabbitTemplate.convertAndSend("myExchange1", "routingKey4", msg, correlationData);
        } catch (AmqpException e) {
            // 存缓存操作
            log.info(msg + "发送失败:原因重连10次都没连上。");
        }
        return "success";
    }
}
