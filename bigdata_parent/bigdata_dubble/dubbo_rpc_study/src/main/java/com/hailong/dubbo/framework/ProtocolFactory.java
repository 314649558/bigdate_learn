package com.hailong.dubbo.framework;

import com.hailong.dubbo.protocol.Protocol;
import com.hailong.dubbo.protocol.http.HttpProtocol;
import com.hailong.dubbo.protocol.netty.NettyProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Administrator on 2021/3/18.
 */
public class ProtocolFactory {


    public static Protocol getProtocol(RemoteURL url){
        return factory();
    }


    /**
     * 通过JAVA SPI的形式获取Protocol
     *
     *
     *
     * @return
     */
    private static Protocol javaspi(){
        //ServiceLoader 加载指定类实现类
        //加载位置 META-INF/services
        ServiceLoader<Protocol> serviceLoader=ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();
    }

    /**
     * 通过工厂模式的方式获取协议对象
     * -DprotocolName=http
     * @return
     */
    private static Protocol factory() {
        String name=System.getProperty("protocolName");
        switch (name){
            case "http":
                return new HttpProtocol();
            case "netty":
                return new NettyProtocol();
            default:
                break;
        }
        return null;
    }
}
