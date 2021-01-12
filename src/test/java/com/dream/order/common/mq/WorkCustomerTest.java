package com.dream.order.common.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiangll01
 * @Date: 2020/11/21 15:36
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkCustomerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void receive() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "hello work!");
        }
    }

    @Test
    public void testFanout() throws InterruptedException {
        rabbitTemplate.convertAndSend("fanout", "", "这是fanout广播");
    }

    @Test
    public void testDirect() {
        rabbitTemplate.convertAndSend("directs", "error", "error 的日志信息");
    }

    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("topics", "user.save.findAll", "user.save.findAll 的消息");
    }

    @Test
    public void MultithreadingTest() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("directs", "multiThreadingKey", "user.save.findAll 的消息");
        }

    }

    /**
     * 第四个参数MessagePostProcessor messagePostProcessor是个函数式接口；
     * 函数式接口可以理解为把方法当做参数传递过来，并且只能有一个抽象方法；
     * 这里用的是lambda表达式语法实现接口的抽象方法，也可以用匿名内部类（不推荐）；
     * Lambda表达式、函数式接口、接口默认方法可参考https://www.runoob.com/java/java8-new-features.html
     **/
    @Test
    public void delayedTest() {
        rabbitTemplate.convertAndSend("delayed", "delayed", "延时队列测试", (message -> {
            // 设置消息的延时时间为30
            message.getMessageProperties().setDelay(5000);
            return message;
        }));
        rabbitTemplate.convertAndSend("delayed", "delayed", "延时队列测试", (message -> {
            // 设置消息的延时时间为30
            message.getMessageProperties().setDelay(1000);
            return message;
        }));
    }

    /**
     * 队列为延迟
     */
    @Test
    public void delayedTest1() {
        rabbitTemplate.convertAndSend("exchange.delay", "key.delay", "延时队列11111测试");
    }
}


