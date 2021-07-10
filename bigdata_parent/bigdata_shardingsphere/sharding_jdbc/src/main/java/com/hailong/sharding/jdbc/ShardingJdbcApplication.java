package com.hailong.sharding.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanhailong
 * @date 2021/7/10.
 */
@SpringBootApplication
@MapperScan("com.hailong.sharding.jdbc.mapper")
public class ShardingJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class,args);
    }
}
