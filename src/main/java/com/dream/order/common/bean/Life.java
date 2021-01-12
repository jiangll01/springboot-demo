package com.dream.order.common.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author jiangll01
 * @Date: 2020/10/13 18:49
 * @Description: 人生
 * 实现了BeanPostProcessor接口:Spring就将调用他们的postProcessBeforeInitialization()方法
 * 、postProcessAfterInitialization()方法。
 */
public class Life implements BeanPostProcessor, InitializingBean, DisposableBean {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * Bean 实现了InitializingBean接口
     * Spring将调用他们的afterPropertiesSet()方法。
     * 类似的，如果bean使用init-method声明了初始化方法，该方法也会被调用
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    /**
     * bean实现了DisposableBean接口
     * Spring将调用它的destory()接口方法，
     * 同样，如果bean使用了destory-method 声明销毁方法，该方法也会被调用。
     */
    @Override
    public void destroy() throws Exception {

    }
}
