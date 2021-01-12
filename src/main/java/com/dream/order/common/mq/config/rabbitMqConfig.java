package com.dream.order.common.mq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author jiangll01
 * @Date: 2020/11/17 19:41
 * @Description: 配置rabbitTemplate模板，让其支持传输对象时候能够序列化
 * * Mandatory为true时,消息通过交换器无法匹配到队列会返回给生产者
 * * 并触发MessageReturn 为false时,匹配不到会直接被丢弃
 * * 但是配置在yaml无效
 */
@Configuration
public class rabbitMqConfig {

    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        //开启交换机到队列的消息是否到达，自动删除不可达消息，默认为false，true 则调用回调函数
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
