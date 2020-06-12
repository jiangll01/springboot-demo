package com.dream.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author jiangll01
 * @Date: 2020/6/11 10:21
 * @Description:
 */
@SpringBootApplication

public class SpringbootDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo.class,args);
        String s = "我要联系会退的版本，你信嘛？";
    }
}
