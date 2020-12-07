package com.bigdata.bdp.hadoop.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocol.ClientProtocol;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2020/12/5.
 */
public class RPCClient {
    public static void main(String[] args) {

        try {
            ClientProtocol proxy = RPC.getProxy(ClientProtocol.class,
                    ClientProtocol.versionID,
                    new InetSocketAddress("localhost", 9006), new Configuration());
            proxy.renewLease(" hailong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
