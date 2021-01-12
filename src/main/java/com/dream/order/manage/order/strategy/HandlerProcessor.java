package com.dream.order.manage.order.strategy;

import com.dream.order.manage.order.annotation.HandlerType;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author jiangll01
 * @Date: 2020/11/18 16:59
 * @Description: 1、扫描指定包中标有@HandlerType的类；
 * <p>
 * 2、将注解中的类型值作为key，对应的类作为value，保存在Map中；
 * <p>
 * 3、以上面的map作为构造函数参数，初始化HandlerContext，将其注册到spring容器中；
 */
@Slf4j
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("******调用了BeanFactoryPostProcessor");
        Map<String, Class> map = Maps.newHashMapWithExpectedSize(3);
        String[] names = configurableListableBeanFactory.getBeanNamesForAnnotation(HandlerType.class);
        if (names == null) {
            return;
        }
        for (String name : names) {
            map.put(configurableListableBeanFactory.getType(name).getAnnotation(HandlerType.class).value(), configurableListableBeanFactory.getType(name));
        }
        HandlerContext handlerContext = new HandlerContext(map);
        configurableListableBeanFactory.registerSingleton(handlerContext.getClass().getName(), handlerContext);


    }


    /**
     * 1. beanFactoryPostProcessor接口可以在bean未被实例化之前获取bean的定义即配置元数据，然后根据需要进行更改。
     * <p>
     * 2. beanFactoryPostProcessor里有方法  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException; 此方法可以通过beanFactory可以获取bean的定义信息，并可以修改bean的定义信息。
     * <p>
     * 3. 可以在spring配置文件中定义多个beanFactoryPostProcessor，执行顺序按照定义顺序执行，也可以根据order属性自己定义执行顺序。
     *
     * @param configurableListableBeanFactory bean工厂
     */
    private void update(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        String[] beanStr = configurableListableBeanFactory
                .getBeanDefinitionNames();
        for (String beanName : beanStr) {
            if ("beanFactoryPostProcessorTest".equals(beanName)) {
                BeanDefinition beanDefinition = configurableListableBeanFactory
                        .getBeanDefinition(beanName);
                MutablePropertyValues m = beanDefinition.getPropertyValues();
                if (m.contains("name")) {
                    m.addPropertyValue("name", "赵四");
                    log.info("》》》修改了name属性初始值了");
                }
            }
        }
    }


}
