package com.dream.order.common.redis.utis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.Map;

//用来获取springboot创建好的工厂
@Component
@SuppressWarnings("unchecked")
public class ApplicationContextUtils implements ApplicationContextAware {

    //保留下来工厂
    private static ApplicationContext applicationContext;

    //将创建好工厂以参数形式传递给这个类
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据bean名字获取spring容器中的bean
     *
     * @param beanName bean名字
     * @return pring bean
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 根据class来获取bean对象
     *
     * @param clazz 类信息
     * @return spring bean
     */
    public static Object getBean(Class clazz) {
        Assert.notNull(clazz, "没有此类活动");
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据class，获取接口的实现类或者类的子类
     *
     * @param clazz 类信息
     * @return 全部实现类或者子类
     */
    public static Map getBeansOfType(Class clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 获取添加注解信息的bean
     *
     * @param clazz 类信息
     * @return 获取添加注解信息的bean集合
     */
    public static Map<String, Object> getBeansWithAnnotation(Class clazz) {
        return applicationContext.getBeansWithAnnotation(clazz);
    }


}
