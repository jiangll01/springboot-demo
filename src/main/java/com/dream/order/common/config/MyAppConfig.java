package com.dream.order.common.config;

import com.dream.order.manage.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangll01
 * @Date: 2020/7/14 16:08
 * @Description:
 */
@Configuration
@ConditionalOnBean
public class MyAppConfig {
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
