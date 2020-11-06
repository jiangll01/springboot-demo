package com.dream.order.common.redis.DelayTask;

        import com.dream.order.common.redis.utis.RedisHelper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;

/**
 * @author jiangll01
 * @Date: 2020/10/21 18:00
 * @Description: 延时任务的生产者
 */
@Component
public class DelayTaskProducer {
    private final String DELAY_QUEUE = "queue";
    @Autowired
    private RedisHelper redisHelper;

    public void putTask(String name, double score) {
        redisHelper.setForZset(DELAY_QUEUE, name, score);
    }
}
