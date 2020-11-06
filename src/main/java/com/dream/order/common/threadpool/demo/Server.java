package com.dream.order.common.threadpool.demo;

import lombok.SneakyThrows;

/**
 * @author jiangll01
 * @Date: 2020/11/3 9:42
 * @Description:
 */
public class Server implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(20);
        System.out.println("打印线程"+Thread.currentThread().getName());
    }
}
