package com.hailong.dubbo.register;

import com.hailong.dubbo.framework.RemoteURL;

import java.util.*;

/**
 * Created by Administrator on 2021/3/18.
 */
public class RemoteMapRegister {

    private static Map<String,List<RemoteURL>> map=new HashMap<>();

    public static void register(String interfaceName,RemoteURL url){
        List<RemoteURL> list= Collections.singletonList(url);
        map.put(interfaceName,list);
    }

    /**
     * 模拟随机获取
     * @param interfaceName
     * @return
     */
    public static RemoteURL random(String interfaceName){
        List<RemoteURL> remoteURLS = map.get(interfaceName);
        Random random=new Random();
        int i = random.nextInt(remoteURLS.size());
        return remoteURLS.get(i);
    }
}
