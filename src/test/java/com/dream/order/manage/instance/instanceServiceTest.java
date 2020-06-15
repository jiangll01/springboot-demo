package com.dream.order.manage.instance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author jiangll01
 * @Date: 2020/6/12 16:54
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class instanceServiceTest {
    @Autowired
    instanceService instanceService;
    @Test
    public void getPerson() {
        instanceService.getPerson();
    }
}
