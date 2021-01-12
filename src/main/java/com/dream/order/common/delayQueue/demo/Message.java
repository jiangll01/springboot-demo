package com.dream.order.common.delayQueue.demo;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/6 15:48
 * @Description: 消息体定义 实现Delayed接口就是实现两个方法即compareTo 和 getDelay最重要的就是getDelay方法，
 * 这个方法用来判断是否到期
 */
@Data
public class Message implements Delayed {
    private int id;
    private String body; // 消息内容
    private long time;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public Message(int id, String body, long time, TimeUnit unit) {
        this.id = id;
        this.body = body;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);//执行的时间点
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed delayed) {
        Message msg = (Message) delayed;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
                : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }
}
