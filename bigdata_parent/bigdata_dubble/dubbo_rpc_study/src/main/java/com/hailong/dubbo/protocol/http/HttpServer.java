package com.hailong.dubbo.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by Administrator on 2021/3/18.
 */
public class HttpServer {

    public void start(String hostname,Integer port){
        Tomcat tomcat=new Tomcat();
        Server server=tomcat.getServer();
        Service service = server.findService("Tomcat");
        server.addService(service);

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine=new StandardEngine();
        engine.setDefaultHost(hostname);

        Host host=new StandardHost();
        host.setName(hostname);

        String contextPath="";
        Context context=new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);
        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");

        //启动tomcat
        try {
            tomcat.start();
            tomcat.getServer().await();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
