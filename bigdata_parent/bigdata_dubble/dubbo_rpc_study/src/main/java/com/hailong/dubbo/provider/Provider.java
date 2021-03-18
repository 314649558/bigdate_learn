package com.hailong.dubbo.provider;

import com.hailong.dubbo.framework.RemoteURL;
import com.hailong.dubbo.protocol.http.HttpServer;
import com.hailong.dubbo.provider.api.HelloService;
import com.hailong.dubbo.provider.impl.HelloServiceImpl;
import com.hailong.dubbo.register.RemoteMapRegister;

/**
 * Created by Administrator on 2021/3/18.
 *
 * 服务提供者
 */
public class Provider {
    public static void main(String[] args) {
        //1.本地注册
        //服务名[接口名称]   实现类
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        //2.远程注册
        //服务名：List<RemoteUrl> 地址
        RemoteMapRegister.register(HelloService.class.getName(),new RemoteURL("localhost",8080));

        //3.启动tomcat
        HttpServer httpServer=new HttpServer();
        httpServer.start("localhost",8080);
    }

}
