package com.bigdata.kafka.sourceparse.utils;

import com.bigdata.kafka.sourceparse.bean.UserBehaviorObj;
import com.google.gson.*;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by Administrator on 2020/2/25.
 */
public class JsonUtils {

    private static final Gson gson=new GsonBuilder()
            .serializeNulls()
            .create();



    public static <T> T  fromJson(String json,Class<T> clazz){
        return gson.fromJson(json,clazz);
    }


    public static <T> String toJson(T bean){
        return gson.toJson(bean);
    }


    public static JsonObject strToJsonObject(String str){
       return new JsonParser().parse(str).getAsJsonObject();
    }

    public static JsonArray strToJsonArray(String str){
        return new JsonParser().parse(str).getAsJsonArray();
    }



    public static void main(String[] args) throws Exception {

        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println(format.format(new java.util.Date()));

    }

}












