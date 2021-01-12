package com.dream.order.common.redis;

import com.dream.order.common.redis.utis.StringRedisHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangll01
 * @Date: 2020/10/21 11:28
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StringRedisHelperTest {

    @Autowired
    StringRedisHelper stringRedisHelper;

    @Test
    public void removeForValue() {

    }

    @Test
    public void getExpireForValue() {
    }

    @Test
    public void isExistForValue() {
    }

    @Test
    public void setForValue() {
        stringRedisHelper.setForValue("我先", "hehhe");
    }

    @Test
    public void getForValue() {
    }

    @Test
    public void multiSetForValue() {
    }

    @Test
    public void multiGetForValue() {
        Map<String, String> map = new HashMap<>();
        map.put("张三", "山东菏泽人");
        map.put("李四", "山东菏泽人");
        stringRedisHelper.multiSetForValue(map);
    }


    @Test
    public void getForValue1() {
    }

    @Test
    public void getStrLen() {
    }

    @Test
    public void setForValue1() {
    }

    @Test
    public void setIfAbsentForValue() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void incr1() {
    }

    @Test
    public void leftPushForList() {
    }

    @Test
    public void leftPushAllForList() {
    }

    @Test
    public void leftPushAllForList1() {
    }

    @Test
    public void rightPushForList() {
    }

    @Test
    public void rightPushAllForList() {
    }

    @Test
    public void rightPushAllForList1() {
    }

    @Test
    public void getForList() {
    }

    @Test
    public void indexForlist() {
    }
}
