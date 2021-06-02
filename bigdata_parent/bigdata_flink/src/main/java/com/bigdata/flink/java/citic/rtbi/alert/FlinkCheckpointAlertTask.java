package com.bigdata.flink.java.citic.rtbi.alert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;
import com.bigdata.flink.java.citic.rtbi.alert.entity.AlertInstance;
import com.bigdata.flink.java.citic.rtbi.alert.entity.HadoopConfig;
import com.bigdata.flink.java.citic.rtbi.alert.entity.StreamInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * flink checkpoint 检查
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class FlinkCheckpointAlertTask extends BaseAlertTask {

    @Autowired
    private HadoopConfig hadoopConfig;

    @Override
    public List<AlertInstance> check(Alert alert) {
        List<AlertInstance> instanceList=new ArrayList<>();
        //实际数据可来源于数据库
        List<StreamInstance> list=new ArrayList<>();

        for(StreamInstance instance:list){
            //查找某个application下所有的flink job
            String url=hadoopConfig.yarnUrl+"/proxy"+instance.getAppid()+"/jobs";
            try {
                RequestEntity<Void> request = RequestEntity.get(new URI(url))
                        .header("Aceept", MediaType.ALL.toString())
                        .build();

                ResponseEntity<JSONObject> jobResponse = restTemplate.exchange(request, JSONObject.class);

                JSONArray jobArray = jobResponse.getBody().getJSONArray("jobs");

                for (int i = 0; i < jobArray.size(); i++) {
                    String status=jobArray.getJSONObject(i).getString("status");
                    if("RUNNING".equalsIgnoreCase(status)) {
                        String jobId = jobArray.getJSONObject(i).getString("id");

                        //获取每个JOB的详细信息
                        String checkpointUri = url + "/" + jobId+"/checkpoints";

                        RequestEntity<Void> checkpointRequest = RequestEntity.get(new URI(checkpointUri))
                                .header("Aceept", MediaType.ALL.toString())
                                .build();

                        ResponseEntity<JSONObject> checkpoints = restTemplate.exchange(checkpointRequest, JSONObject.class);

                        JSONObject lastest = checkpoints.getBody().getJSONObject("lastest");

                        if(null !=lastest.getString("failed")){
                            if(lastest.getString("completed")!=null){
                                JSONObject completed = lastest.getJSONObject("completed");

                                //如果有完成的就回复
                                if(completed.getInteger("id")-lastest.getJSONObject("failed").getInteger("id")>1){
                                    continue;
                                }
                            }

                            AlertInstance alertInstance=new AlertInstance();
                            alert.setName("checkpoint告警");
                            alertInstance.setLevel(Alert.Level.DANGER);
                            alertInstance.setMessage("checkpoint失败");
                            instanceList.add(alertInstance);
                        }
                    }
                }
            }catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return instanceList;
    }
}
