package com.dream.order.manage.thread.demo1;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jiangll01
 * @Date: 2020/6/16 11:34
 * @Description:
 */
public class ThreadPoolDemo {
    private ThreadPoolExecutor poolExecutor;

    public ThreadPoolDemo(ThreadPoolExecutor poolExecutor) {
        this.poolExecutor = poolExecutor;
    }

    public void getName(Runnable rb) {
        System.out.println(poolExecutor.hashCode());
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池：。。。。。。。。。");
                System.out.println(Thread.currentThread().getName());
            }
        });
        poolExecutor.shutdown();
    }
}
