package com.hailong.dubbo.protocol.netty;

import com.hailong.dubbo.framework.Invocation;
import com.hailong.dubbo.provider.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2021/3/18.
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<String> {
    private Invocation invocation;

    @Override
    public String call() throws Exception {
        return null;
    }
}
