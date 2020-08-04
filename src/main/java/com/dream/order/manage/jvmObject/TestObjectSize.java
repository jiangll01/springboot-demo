package com.dream.order.manage.jvmObject;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @author jiangll01
 * @Date: 2020/7/9 11:16
 * @Description:
 *  * 是否开启指针压缩 -XX:-UseCompressedOops,需要关闭指针压缩
 *  *
 * <dependency>
 *     <groupId>org.openjdk.jol</groupId>
 *     <artifactId>jol-core</artifactId>
 *     <version>0.9</version>
 * </dependency>
 *  */
public class TestObjectSize {
    public static void main(String[] args) {
        System.out.println("aaaaa");
        //查看对象内部信息
        TestObjectSize testObjectSize = new TestObjectSize();

        System.out.println(ClassLayout.parseInstance(testObjectSize).toPrintable());
        System.out.println("=================");
        synchronized (testObjectSize){
            System.out.println(ClassLayout.parseInstance(testObjectSize).toPrintable());
        }
        System.out.println("=================");

        System.out.println(GraphLayout.parseInstance(testObjectSize).toPrintable());
        System.out.println("=================");
        System.out.println(GraphLayout.parseInstance(testObjectSize).totalSize());

        System.out.println(ClassLayout.parseInstance(new Object[0]).toPrintable());



    }


}
