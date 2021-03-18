package com.hailong.dubbo.consumer;

import com.hailong.dubbo.framework.Invocation;
import com.hailong.dubbo.framework.ProxyFactory;
import com.hailong.dubbo.protocol.http.HttpClient;
import com.hailong.dubbo.provider.api.HelloService;

/**
 * Created by Administrator on 2021/3/18.
 */
public class Consumer {
    public static void main(String[] args) {

        //methodV1();

        methodV2();

    }

    private static void methodV2() {
        HelloService helloService= ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("hailong");
        System.out.println(result);
    }


    /**
     * 使用代理对象
     */
    private static void methodV1() {
        HttpClient httpClient=new HttpClient();
        Invocation invocation=new Invocation(HelloService.class.getName(),"sayHello",new Class[]{String.class},new Object[]{" hailong "});
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println("client:"+result);
    }
}
