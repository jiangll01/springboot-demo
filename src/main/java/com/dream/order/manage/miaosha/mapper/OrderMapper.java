package com.dream.order.manage.miaosha.mapper;

import com.dream.order.manage.miaosha.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jiangll01
 * @Date: 2020/11/10 10:50
 * @Description:
 */
@Repository
@Mapper
public interface OrderMapper {
    void createOrder(Order order);
}
