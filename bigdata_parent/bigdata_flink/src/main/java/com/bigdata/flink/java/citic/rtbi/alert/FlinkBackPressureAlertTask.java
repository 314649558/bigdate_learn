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
 *
 * Flink 背压检查
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class FlinkBackPressureAlertTask extends BaseAlertTask {

    @Autowired
    private HadoopConfig hadoopConfig;

    @Override
    public List<AlertInstance> check(Alert alert) {
        List<AlertInstance> instanceList=new ArrayList<>();

        //从数据库中查询出正在运行的任务
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

                for(int i=0;i<jobArray.size();i++){
                    String status=jobArray.getJSONObject(i).getString("status");
                    if("RUNNING".equalsIgnoreCase(status)){
                        String jobId=jobArray.getJSONObject(i).getString("id");

                        //获取每个JOB的详细信息
                        String overviewUri=url+"/"+jobId;

                        RequestEntity<Void> jobRequest = RequestEntity.get(new URI(overviewUri))
                                .header("Aceept", MediaType.ALL.toString())
                                .build();
                        //job的信息信息
                        ResponseEntity<JSONObject> jobDetail = restTemplate.exchange(jobRequest, JSONObject.class);

                        JSONArray verticeArray = jobDetail.getBody().getJSONArray("vertices");

                        for(int j=0;j<verticeArray.size();j++){
                            String id=verticeArray.getJSONObject(j).getString("id");
                            //得到背压的API
                            String backPressureUri=overviewUri+"/vertices/"+id+"/backpressure";

                            RequestEntity<Void> backPressureRequest = RequestEntity.get(new URI(backPressureUri))
                                    .header("Accept", MediaType.ALL.toString())
                                    .build();

                            while(true){
                                ResponseEntity<JSONObject> backPressure = restTemplate.exchange(backPressureRequest, JSONObject.class);
                                if(!"deprecated".equalsIgnoreCase(backPressure.getBody().getString("status"))){
                                    //以主任务为准，不一分区为准
                                    String bkLevel = backPressure.getBody().getString("backpressure-level");
                                    if("LOW".equalsIgnoreCase(bkLevel)||
                                            "HIGH".equalsIgnoreCase(bkLevel)){
                                        AlertInstance alertInstance=new AlertInstance();
                                        alertInstance.setName("背压告警");
                                        boolean isDanger = "HIGH".equalsIgnoreCase(bkLevel);
                                        alertInstance.setLevel(isDanger?Alert.Level.DANGER:Alert.Level.WARNING);
                                        alertInstance.setMessage(verticeArray.getJSONObject(j).getString("name")+" 算子出现背压");
                                        instanceList.add(alertInstance);
                                    }
                                }
                            }

                        }


                    }
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
