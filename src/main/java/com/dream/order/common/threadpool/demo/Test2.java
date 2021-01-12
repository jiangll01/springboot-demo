package com.dream.order.common.threadpool.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangll01
 * @Date: 2020/11/3 10:12
 * @Description:
 */
public class Test2 {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("测试线程%d").build();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, threadFactory);
        Server server = new Server();
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(server, 0L, 1L, TimeUnit.SECONDS);
    }
}
