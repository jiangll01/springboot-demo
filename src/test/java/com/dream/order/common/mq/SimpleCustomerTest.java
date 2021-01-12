package com.dream.order.common.mq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author jiangll01
 * @Date: 2020/11/19 22:17
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleCustomerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void receive1() {
        MessageProperties messageProperties = new MessageProperties();
        //消息持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message message = new Message(("点对点的队列方式，设置了消息持久化").getBytes(), messageProperties);
        //rabbitTemplate.convertAndSend("simple",message);
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("simple", "点对点的队列方式");
        }
    }
}
