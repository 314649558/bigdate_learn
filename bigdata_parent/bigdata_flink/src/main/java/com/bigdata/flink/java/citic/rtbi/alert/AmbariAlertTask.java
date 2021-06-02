package com.bigdata.flink.java.citic.rtbi.alert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;
import com.bigdata.flink.java.citic.rtbi.alert.entity.AlertInstance;
import com.twitter.chill.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ambari 告警
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class AmbariAlertTask extends BaseAlertTask {

    @Value("${ambari.url}/api/v1/culsters/${ambari.cluster}/alerts?fields=*Alert/state.in(WARNING.CRTICAL)&Alert/maintenance_state.in(OFF)")
    private String url;

    @Value("${ambari.username}")
    private String username;

    @Value("${ambari.password}")
    private String password;




    @Override
    public List<AlertInstance> check(Alert alert) {

        List<AlertInstance> instanceList=new ArrayList<>();

        String auth= Base64.encodeBytes((username+":"+password).getBytes());

        ResponseEntity<String> response=null;

        String errorMessage=null;


        try {

            //利用Rest API 从Ambari上获取告警信息
            RequestEntity<Void> request = RequestEntity.get(new URI(url))
                    .header("Authorization", "Basic " + auth)
                    .header("Accept", MediaType.ALL.toString())
                    .build();

            response=restTemplate.exchange(request,String.class);

        } catch (URISyntaxException e) {
            System.out.println("获取告警信息失败");
            errorMessage=e.toString();
        }


        if(response==null ||response.getStatusCode()!= HttpStatus.OK){
            String name="获取告警信息失败";
            String message=response==null?errorMessage:response.getStatusCode().toString();
            AlertInstance alertInstance=new AlertInstance();
            alertInstance.setName(name);
            alertInstance.setLevel(Alert.Level.WARNING);
            alertInstance.setMessage(message);
            instanceList.add(alertInstance);
        }else{
            JSONObject jsonObject = JSON.parseObject(response.getBody());

            JSONArray items=jsonObject.getJSONArray("items");

            for(int i=0;i<items.size();i++){
                JSONObject item = items.getJSONObject(i).getJSONObject("Alert");
                String name = item.getString("definition_name");
                String host = item.getString("host_name");

                boolean isDanger = "CRITICAL".equals(item.getString("state"));


                AlertInstance alertInstance=new AlertInstance();
                alertInstance.setName(name);
                alertInstance.setLevel(isDanger?Alert.Level.DANGER:Alert.Level.WARNING);
                alertInstance.setMessage(item.getString("text"));
                instanceList.add(alertInstance);
            }
        }
        return instanceList;
    }
}
