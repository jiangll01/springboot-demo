/*
package com.dream.order.common.mq.demo1.config;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DirectExchangeConfig {
    */
/*
     * create DelayQueue
     * @return {@link Queue}
     *//*


    @Bean
    public Queue delayedQueue() {
        Map<String, Object> argsMap = Maps.newHashMap();
        argsMap.put("x-dead-letter-exchange", "exchange.delay.dead");
        argsMap.put("x-dead-letter-routing-key", "key.delay.dead");
        argsMap.put("x-message-ttl", 60000);
        return new Queue("queue.111", true, false, false, argsMap);


    }


    @Bean(name = "directExchange")
    public DirectExchange directExchange() {
        return new DirectExchange("exchange.delay");
    }

    @Bean
    Binding bindingDelayQueue() {
        return BindingBuilder.bind(delayedQueue()).to(directExchange())
                .with("key.delay");
    }

}
*/
