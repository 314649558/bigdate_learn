package com.hailong.spi.java;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Administrator on 2021/3/18.
 */
public class CarDemo {
    public static void main(String[] args) {
        //利用JAVA SPI获取扩展接口实现类
        //JAVA SPI扩展接口有以下缺点
        // 1 无法获得某个特定的扩展类
        // 2 无法利用AOP和IOC编程
        ServiceLoader<Car> serviceLoader=ServiceLoader.load(Car.class);

        Iterator<Car> iterator = serviceLoader.iterator();

        while (iterator.hasNext()){
            iterator.next().getColor();
        }

    }
}
