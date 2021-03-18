package com.hailong.dubbo.provider.impl;

import com.hailong.dubbo.provider.api.HelloService;

/**
 * Created by Administrator on 2021/3/18.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }
}
