package com.hailong.dubbo.protocol.http;

import com.hailong.dubbo.framework.Invocation;
import com.hailong.dubbo.provider.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2021/3/18.
 */
public class HttpServerHandler {

    public void handler(ServletRequest req, ServletResponse resp){
        //处理请求，返回结果
        try {
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream ois=new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation)ois.readObject();

            Class impClass= LocalRegister.get(invocation.getInterfaceName());
            Method method = impClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String)method.invoke(impClass.newInstance(), invocation.getArgs());
            //返回结果
            IOUtils.write(result,resp.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
