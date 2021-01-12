package com.dream.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jiangll01
 * @Date: 2020/6/11 10:21
 * @Description:
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringbootDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo.class, args);

    }

}
