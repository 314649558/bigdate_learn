package com.hailong.spi.dubbo;

/**
 * Created by Administrator on 2021/3/18.
 */
public class HorseAnimal implements Animal {
    @Override
    public void name() {
        System.out.println("Horse");
    }
}
