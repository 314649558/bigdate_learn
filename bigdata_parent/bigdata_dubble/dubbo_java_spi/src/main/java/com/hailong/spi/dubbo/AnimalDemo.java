package com.hailong.spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * Created by Administrator on 2021/3/18.
 */
public class AnimalDemo {

    public static void main(String[] args) {

        ExtensionLoader<Animal> loader=ExtensionLoader.getExtensionLoader(Animal.class);
        Animal horse = loader.getExtension("horse");  //根据KEY获取指定的实现类

        //添加一个AnimalWrapper并将其添加到spi的文件中就会有AOP的效果
        horse.name();
    }
}
