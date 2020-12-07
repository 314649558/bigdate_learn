package com.bigdata.bdp.hadoop.rpc;

import com.bigdata.bdp.hadoop.rpc.ClientProtocolImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocol.ClientProtocol;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * Created by Administrator on 2020/12/5.
 */
public class RPCServer {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        try {
            RPC.Server rpcServer = new RPC.Builder(configuration).setProtocol(ClientProtocol.class)
                    .setInstance(new ClientProtocolImpl())
                    .setBindAddress("localhost")
                    .setPort(9006)
                    .setNumHandlers(5)
                    .build();
            rpcServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
