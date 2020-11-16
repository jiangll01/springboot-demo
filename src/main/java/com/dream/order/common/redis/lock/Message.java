package com.dream.order.common.redis.lock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/6 16:52
 * @Description:
 */
@Data
@Accessors(chain = true)
public class Message implements Delayed {
    private int id;

    private String body;

    private long time;

    public Message(int id, String body, long time, TimeUnit timeUnit) {
        this.id = id;
        this.body = body;
        this.time = System.currentTimeMillis() + (time > 0 ? timeUnit.toMillis(time) : 0);
    }
    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    // 进行消息在队列中进行排序
    @Override
    public int compareTo(Delayed delayed) {
        Message msg = (Message) delayed;
        // 改成>=会造成问题
        if (this.time - msg.time <= 0) {
            return -1;
        }else {
            return 1;
        }
    }
}
