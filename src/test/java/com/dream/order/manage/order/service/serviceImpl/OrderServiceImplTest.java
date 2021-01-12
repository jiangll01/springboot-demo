package com.dream.order.manage.order.service.serviceImpl;

import com.dream.order.manage.order.bean.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author jiangll01
 * @Date: 2020/11/19 14:26
 * @Description:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void handle() {
        OrderDTO orderDTO = new OrderDTO();
        orderService.handle(orderDTO);
    }

    @Test
    public void handle1() {
//        given(defenseMapper.getDefenseConfig(anyString(),anyString(),anyString())).willReturn(configurations);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setType("3");
        assertEquals("处理促销订单", orderService.handle1(orderDTO));
    }
}
