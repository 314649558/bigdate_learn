package com.hailong.spi.dubbo;

/**
 * Created by Administrator on 2021/3/18.
 */
public class MokeyAnimal implements Animal {
    @Override
    public void name() {
        System.out.println("Mokey");
    }
}
