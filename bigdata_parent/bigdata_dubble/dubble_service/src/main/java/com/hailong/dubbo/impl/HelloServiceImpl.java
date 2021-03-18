package com.hailong.dubbo.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hailong.dubbo.provider.HelloService;

import java.util.Date;

/**
 * Created by Administrator on 2021/3/17.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println(new Date()+"\t"+RpcContext.getContext().getRemoteAddress());
        return "hello "+ name + RpcContext.getContext().getRemoteAddress();
    }
}
