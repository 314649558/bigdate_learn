package com.hailong.dubbo.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2021/3/18.
 */
@Data
@AllArgsConstructor
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramTypes;
    private Object[] args;
}
