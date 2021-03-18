package com.hailong.dubbo.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.hailong.dubbo.provider.HelloService;
import com.alibaba.dubbo.config.RegistryConfig;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2021/3/17.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        startWithExport();
    }


    private static void startWithExport() throws Exception {
        ServiceConfig<HelloServiceImpl> service=new ServiceConfig<>();
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImpl());
        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://192.168.112.101:2181"));
        service.export();
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }

}
