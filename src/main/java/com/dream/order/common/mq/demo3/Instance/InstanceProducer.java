package com.dream.order.common.mq.demo3.Instance;

import com.dream.order.common.mq.demo3.Constant.MqConstant;
import com.dream.order.common.mq.demo3.Exception.MyException;
import com.dream.order.common.mq.demo3.bean.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jiangll01
 * @Date: 2020/11/23 16:15
 * @Description: 消费成功后向生产者发送消费成功消息
 */
@Component
@Slf4j
public class InstanceProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public InstanceProducer(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setMandatory(true);
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(Instance msg, CorrelationData data) {
        log.info("send confirm message to producer{}", msg.toString());
        //确认是否发到交换机
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                throw new MyException("consumer message sent to queue failed");
                //todo 要不要考虑消息重发 操作数据库，将 correlationId 这条消息状态改为投递失败
            }

        });
        //确认是否路由到了队列
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            // todo 操作数据库，将 correlationId 这条消息状态改为投递失败
            throw new MyException("post queue failed");
        });
        //给生产者应答
        rabbitTemplate.convertAndSend(MqConstant.EXCHANGE_CONSOLE_ORDER, MqConstant.NOTIFY_ORDER_ROUTINGKEY, msg, data);
    }
}
