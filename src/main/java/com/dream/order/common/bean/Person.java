package com.dream.order.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jiangll01
 * @Date: 2020/6/12 16:28
 * @Description: PropertySource：读取外部资源文件
 */
//@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Person implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {
    private String name;
    private String age;
    private String sex;
    private Map<String, Object> map;
    private List<String> annimals;

    /**
     * 实现 BeanNameAware： Spring将Bean的Id传递给setBeanName()方法
     * 实现BeanFactoryAware：Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入
     * 实现setApplicationContext：Spring将调用Bean的setApplicationContext()方法，
     * 将bean所在应用上下文引用传入进来。
     */
    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
