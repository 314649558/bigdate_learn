package com.bigdata.bdp.hadoop.yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;

/**
 * Created by Administrator on 2020/12/5.
 */
public class TestYarnClient {
    public static void main(String[] args) throws Exception {
        YarnClient yarnClient = YarnClient.createYarnClient();

        yarnClient.init(new Configuration());
        yarnClient.start();


        YarnClientApplication application = yarnClient.createApplication();

        ApplicationSubmissionContext applicationSubmissionContext = application.getApplicationSubmissionContext();

        ApplicationId applicationId = applicationSubmissionContext.getApplicationId();

        applicationSubmissionContext.setApplicationName("test_yarn_client");

        System.out.println(applicationId.getId());

        yarnClient.submitApplication(applicationSubmissionContext);

    }
}
