package com.hailong.spi.dubbo;

/**
 * Created by Administrator on 2021/3/18.
 */
public class AnimalWrapper implements Animal {

    private Animal animal;

    public AnimalWrapper(Animal animal){
        this.animal=animal;
    }

    @Override
    public void name() {
        System.out.println("begin...");
        animal.name();
        System.out.println("end ...");
    }
}
