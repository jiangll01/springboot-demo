package com.dream.order.common.redis.DelayTask;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/10/21 18:00
 * @Description: 延时任务的消费者
 */
@Component
@Slf4j
public class DelayTaskConsumer implements Runnable , BeanPostProcessor, ApplicationContextAware {
    private final String DELAY_QUEUE = "queue";
   @Autowired
    private RedisTemplate redisTemplate;
   private ApplicationContext applicationContext;

    public void run() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        Set set;
        try {
            set = redisTemplate.opsForZSet().rangeByScore(DELAY_QUEUE,0,System.currentTimeMillis(),0,1);
            if (set == null || set.isEmpty()) {
                return;
            }
            for (Object id : set) {
                Long count = redisTemplate.opsForZSet().remove(DELAY_QUEUE, (String) id);
                if (count != null && count == 1) {
                    log.info("延迟后进行了处理 {}",id);
                }
            }
        }catch (Exception e){}finally {

        }

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("delayTaskProducer".equals(beanName)){
            log.info("执行bean的后置处理器");
            log.info("bean对象{}，bean的名字{}",bean,beanName);
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("延时队列%d").build();
            ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, threadFactory);
            DelayTaskConsumer consumer = (DelayTaskConsumer)getBean("delayTaskConsumer");
            poolExecutor.scheduleWithFixedDelay(consumer,0L,1L,TimeUnit.SECONDS);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String beanName){
       return applicationContext.getBean(beanName);
    }
}

