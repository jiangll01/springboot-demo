package com.dream.order.manage.order.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:25
 * @Description: 这里虚拟一个业务需求，让大家容易理解。
 * 假设有一个订单系统，里面的一个功能是根据订单的不同类型作出不同的处理。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDTO {
    private String code;

    /**
     * 订单类型
     * 1、普通订单
     * 2、团购订单
     * 3、促销订单
     */
    @NonNull
    private String type;

    private BigDecimal price;
}
