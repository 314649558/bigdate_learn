package com.hailong.dubbo.protocol.netty;

import com.hailong.dubbo.framework.Invocation;
import com.hailong.dubbo.provider.LocalRegister;
import com.hailong.dubbo.register.RemoteMapRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2021/3/18.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public NettyServerHandler(String hostname,Integer port){
        //RemoteMapRegister.register(new );
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation=(Invocation)msg;

        Class serviceImpl= LocalRegister.get(invocation.getInterfaceName());

        Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamTypes());

        Object result = method.invoke(serviceImpl.newInstance(), invocation.getArgs());

        System.out.println("Netty Server Protocal:"+result.toString());

        ctx.writeAndFlush("Netty:"+result);
    }
}
