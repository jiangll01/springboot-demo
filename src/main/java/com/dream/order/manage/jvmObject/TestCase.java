package com.dream.order.manage.jvmObject;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @author jiangll01
 * @Date: 2020/7/9 16:37
 * @Description:
 */
public class TestCase {
    public static void main(String[] args) {
        TestCase testCase = new TestCase();
        synchronized (testCase) {
            System.out.println(ClassLayout.parseInstance(testCase).toPrintable());
        }
        System.out.println(GraphLayout.parseInstance(testCase).toPrintable());
    }
}
