package com.dream.order.common.redis.lock;

import com.dream.order.common.redis.utis.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

/**
 * @author jiangll01
 * @Date: 2020/11/6 17:05
 * @Description: 定义一个延时任务管理类DelayQueueManager，通过@Component注解加入到spring中管理，
 * 在需要使用的地方通过@Autowire注入，DelayQueueManager实现了CommandLineRunner接口，
 * 在springboot启动完成后就会自动调用run方法。
 */
@Slf4j
@Component
public class DelayQueueManager implements CommandLineRunner {

    private DelayQueue<Message> delayQueue = new DelayQueue<>();

    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1],ARGV[2]) else return '0' end";


    /**
     * 加入到延时队列中
     *
     * @param message
     */
    public void put(Message message) {
        log.info("加入延时任务：{}", message);
        delayQueue.put(message);
    }

    /**
     * 取消延时任务
     *
     * @param message
     * @return
     */
    public boolean remove(Message message) {
        log.info("取消延时任务：{}", message);
        return delayQueue.remove(message);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("项目启动时:运行了");
        Executors.newSingleThreadExecutor().execute(new Thread(this::excuteThread));
    }

    /**
     * 延时任务执行线程
     */
    private void excuteThread() {
        while (true) {
            try {
                Message message = delayQueue.take();
                processTask(message);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * 内部执行延时任务
     *
     * @param message
     */
    private void processTask(Message message) {
        log.info("执行延时任务：{}", message);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
        //通过lua脚本实现延迟时间
      /*  redisTemplate.execute()
        redisDistributedLock.*/
    }
}
