package com.dream.order.manage.service;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author jiangll01
 * @Date: 2020/7/14 15:36
 * @Description:
 */
public class HelloService {
    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty(null));
        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.isEmpty(" "));
        Arrays.asList(1,2,3);
        Map<String, String> map = Maps.newHashMap("1", "1");
    }
}
