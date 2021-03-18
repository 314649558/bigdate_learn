package com.hailong.dubbo.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Administrator on 2021/3/18.
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServerHandler httpServerHandler=new HttpServerHandler();
        httpServerHandler.handler(servletRequest,servletResponse);
    }
}
