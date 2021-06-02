package com.bigdata.flink.java.citic.rtbi.real.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
@EnableSpringBootAppllication
public class StreamApplication {

    public static void main(String[] args) {


        SpringApplication application=new SpringApplication(StreamApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.setRegisterShutdownHook(false);
        ConfigurableApplicationContext applicationContext = application.run(args);
        StreamApplication bean = applicationContext.getBean(StreamApplication.class);
        bean.start(args);

    }

    /**
     * 业务代码
     * @param args
     */
    private void start(String[] args) {
    }

}
