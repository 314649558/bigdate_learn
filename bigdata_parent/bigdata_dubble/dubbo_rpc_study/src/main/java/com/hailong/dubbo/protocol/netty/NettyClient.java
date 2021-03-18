package com.hailong.dubbo.protocol.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2021/3/18.
 */
public class NettyClient {

    public NettyClientHandler client=null;

    private static ExecutorService executorService= Executors.newCachedThreadPool();

    public void start(String hostname,Integer port){
        client=new NettyClientHandler();

        Bootstrap bootstrap=new Bootstrap();
        EventLoopGroup group=new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder",new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast("encoder",new ObjectEncoder());
                        pipeline.addLast("handler",client);
                    }
                });

        try {
            bootstrap.connect(hostname,port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

  /*  public String send(String hostname, Integer port, Invocation invocation){
        if(client==null){
            start(hostname,port);
        }

    }*/

}
