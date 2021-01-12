package com.dream.order.common.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author jiangll01
 * @Date: 2020/7/14 14:41
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)

public class PersonTest {

    @Autowired
    Person person;
    @Autowired
    ApplicationContext ioc;

    @Test
    public void getPerson() {
        System.out.println(person.toString());
    }

    @Test
    public void getHelloService() {
        System.out.println(ioc.containsBean("helloService"));
    }
}
