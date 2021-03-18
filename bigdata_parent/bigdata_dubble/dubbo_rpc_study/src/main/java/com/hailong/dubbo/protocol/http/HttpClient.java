package com.hailong.dubbo.protocol.http;

import com.hailong.dubbo.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2021/3/18.
 */
public class HttpClient {


    public String send(String hostname, Integer port, Invocation invocation){
        try {
            URL url=new URL("http",hostname,port,"/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();

            ObjectOutputStream ous=new ObjectOutputStream(outputStream);
            ous.writeObject(invocation);
            ous.flush();
            ous.close();


            //获取服务端结果返回结果
            InputStream inputStream = httpURLConnection.getInputStream();
            String s = IOUtils.toString(inputStream);
            return s;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
