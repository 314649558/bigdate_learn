package com.bigdata.kafka.sourceparse.producer;

import com.bigdata.kafka.sourceparse.KafkaConstants;
import com.bigdata.kafka.sourceparse.bean.UserBehaviorObj;
import com.bigdata.kafka.sourceparse.utils.JsonUtils;
import kafka.utils.Json;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by 袁海龙 on 2018/11/15.
 */
public class KafkaProducerDemo {

    static SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args) {
        Map<String,Object> kafkaParam=new HashMap<>();
        String TOPIC=args.length==0?KafkaConstants.TOPIC:args[0];
        System.out.println("TOPIC:"+TOPIC);
        kafkaParam.put("bootstrap.servers", KafkaConstants.KAFKA_BOOTSTRAP_SERVER);
        kafkaParam.put("key.serializer", KafkaConstants.KAFKA_SERIALIZER);
        kafkaParam.put("value.serializer", KafkaConstants.KAFKA_SERIALIZER);
        //自定义拦截器
        //kafkaParam.put("interceptor.classes", "com.bigdata.kafka.sourceparse.producer.MyProducerInterceptor");
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<String, String>(kafkaParam);
        int index=0;
        while (index<10000000) {
            String msg = genMessage();
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, msg);
            kafkaProducer.send(record);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        kafkaProducer.close();
    }
    /**
     * 构造消息
     * @return
     */
    public static  String genMessage(){
        Date date=new Date();
        StringBuffer ts=new StringBuffer();
        ts.append(sdf1.format(date))
                .append("T")
                .append(sdf2.format(date))
                .append("Z");
        String[] behavior={"pv","fav","chat","uv"};
        UserBehaviorObj userBehaviorObj=new UserBehaviorObj();
        userBehaviorObj.setUser_id(new Random().nextInt(1000000));
        userBehaviorObj.setItem_id(new Random().nextInt(1000000));
        userBehaviorObj.setCategory_id(new Random().nextInt(1000000));
        userBehaviorObj.setBehavior(behavior[new Random().nextInt(behavior.length)]);
        userBehaviorObj.setTs(ts.toString());
        String str=JsonUtils.toJson(userBehaviorObj);
        System.out.println(str);
        return str;
    }



}
