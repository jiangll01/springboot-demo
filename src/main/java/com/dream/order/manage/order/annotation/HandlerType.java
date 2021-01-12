package com.dream.order.manage.order.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:40
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface HandlerType {
    String value();
}
