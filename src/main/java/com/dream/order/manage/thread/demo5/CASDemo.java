package com.dream.order.manage.thread.demo5;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jiangll01
 * @Date: 2020/6/24 10:25
 * @Description: CAS的学习
 */

public class CASDemo {
    String name;
    int age;

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        int increment = integer.getAndIncrement();
        integer.compareAndSet(0, 1);
        //原子引用，针对常规的数据类型的，自定义类型数据
        CASDemo casDemo = new CASDemo();
        AtomicReference<CASDemo> atomicReference = new AtomicReference<>();
        atomicReference.set(casDemo);
        //atomicStampedReference 通过版本控制来实现解决ABAw问题
        ArrayList<Object> list = new ArrayList<>();
        //创建线程同步、安全的list 通过collections工具类
        List<Object> list1 = Collections.synchronizedList(new ArrayList<>());
        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        //通过juc包下的
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        objects.add("11");
        CopyOnWriteArraySet<Object> set1 = new CopyOnWriteArraySet<>();

    }
}
