package com.hailong.dubbo.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2021/3/18.
 */
public class LocalRegister {

    private static Map<String,Class> map=new HashMap<>();

    public static void register(String interfaceName,Class implClass){
        map.put(interfaceName,implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }


}
