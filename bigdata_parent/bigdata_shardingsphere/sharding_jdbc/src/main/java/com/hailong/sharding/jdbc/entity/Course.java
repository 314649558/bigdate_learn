package com.hailong.sharding.jdbc.entity;

import lombok.Data;

/**
 * @author yuanhailong
 * @date 2021/7/10.
 */
@Data
public class Course {
    private Long cid;   //这里不要用long  因为long的默认值为0 会导致自动生成ID的时候无效
    private String cname ;
    private Long userId ;
    private String cstatus ;
}
