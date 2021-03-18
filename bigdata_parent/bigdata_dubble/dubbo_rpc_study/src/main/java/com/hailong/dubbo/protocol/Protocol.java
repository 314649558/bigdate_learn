package com.hailong.dubbo.protocol;

import com.hailong.dubbo.framework.RemoteURL;

/**
 * 协议接口
 * @author yuanhailong
 */
public interface Protocol {
    void start(RemoteURL url);
    String send(RemoteURL url);
}
