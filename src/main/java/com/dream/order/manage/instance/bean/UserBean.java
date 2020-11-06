package com.dream.order.manage.instance.bean;

import com.dream.order.manage.timezone.bean.TimeBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jiangll01
 * @Date: 2020/11/4 16:37
 * @Description:
 */
@Setter
@Getter
public class UserBean extends TimeBean {
    private String name;
    private String age;
}
