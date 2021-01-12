package com.dream.order.manage.factory;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangll01
 * @Date: 2021/1/7 14:31
 * @Description: 策略模式上下文
 */
@Component
public class Context implements ICarFactory {

    private static Map<String, Car> map = new ConcurrentHashMap<>();

    public Context(List<Car> list) {
        Optional.ofNullable(list).ifPresent(list1 -> {
            list1.forEach(car -> {
                String name = car.getClass().getName();
                String[] strings = name.split("\\.");
                map.put(strings[strings.length - 1], car);
            });
        });
    }

    @Override
    public Car getCar(String carName) {
        return map.get(carName);
    }
}
