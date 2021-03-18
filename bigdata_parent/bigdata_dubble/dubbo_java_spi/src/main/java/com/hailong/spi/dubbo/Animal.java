package com.hailong.spi.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * Created by Administrator on 2021/3/18.
 */
@SPI
public interface Animal {
    void name();
}
