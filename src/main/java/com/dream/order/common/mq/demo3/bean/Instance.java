package com.dream.order.common.mq.demo3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author jiangll01
 * @Date: 2020/11/23 17:05
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Instance {
    private String instanceId;
    private String name;
    private Date createTime;

}
