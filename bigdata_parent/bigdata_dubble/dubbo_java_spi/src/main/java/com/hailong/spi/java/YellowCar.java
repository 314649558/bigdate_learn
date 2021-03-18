package com.hailong.spi.java;

/**
 * Created by Administrator on 2021/3/18.
 */
public class YellowCar implements Car {
    @Override
    public void getColor() {
        System.out.println("Yellow");
    }
}
