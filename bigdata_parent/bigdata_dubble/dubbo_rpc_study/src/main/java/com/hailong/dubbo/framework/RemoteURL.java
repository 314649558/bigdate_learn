package com.hailong.dubbo.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 请求地址封装
 * @author yuanhailong
 */
@Data
@AllArgsConstructor
public class RemoteURL {
    private String hostname;
    private int port;
}
