package com.hailong.dubbo.protocol.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by Administrator on 2021/3/18.
 */
public class NettyServer {
    public void start(String hostname,Integer port){
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        EventLoopGroup mainGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        serverBootstrap.group(mainGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<ServerSocketChannel>() {
                    @Override
                    protected void initChannel(ServerSocketChannel serverSocketChannel) throws Exception {
                        ChannelPipeline pipeline = serverSocketChannel.pipeline();
                        pipeline.addLast("decoder",new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast("encoder",new ObjectEncoder());
                        //pipeline.addLast(new NettyServerHandler());
                    }
                });

        try {
            serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
