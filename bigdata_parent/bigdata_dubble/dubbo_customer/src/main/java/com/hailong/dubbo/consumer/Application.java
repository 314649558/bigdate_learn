package com.hailong.dubbo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.hailong.dubbo.provider.HelloService;

/**
 * Created by Administrator on 2021/3/17.
 */
public class Application {
    public static void main(String[] args) {
        ReferenceConfig<HelloService> reference=new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("dubbo-demo-api-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://192.168.112.101:2181"));
        reference.setInterface(HelloService.class);
        HelloService helloService = reference.get();
        String msg = helloService.sayHello("hailong");
        System.out.println(msg);
    }
}
