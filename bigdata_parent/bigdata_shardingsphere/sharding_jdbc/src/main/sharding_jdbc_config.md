# **按照水平分表的方式，创建数据库和数据库表**

## 描述

1) 创建数据库course_db

2)在数据库中创建两张表course_1和course_2

3)约定规则：如果id是奇数则将数据添加到course_1,偶数添加到course_2

## 创建表

`create table course_1(`
	`cid bigint(20) primary key,`
    `cname varchar(50) not null,`
    `user_id bigint(20) not null,`
    `cstatus varchar(20) not null`
`)`



`create table course_2(`
	`cid bigint(20) primary key,`
    `cname varchar(50) not null,`
    `user_id bigint(20) not null,`
    `cstatus varchar(20) not null`
`)`





## 配置Sharding JDBC 分片策略

1) 在项目applicaiton.properties配置文件中进行配置



配置示例官方手册

![1625903692939](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1625903692939.png)



2）配置参数application.properites









## 水平分库

创建2个数据库

![1625910231298](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1625910231298.png)



 



### 创建数据库和表

![1625910468166](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1625910468166.png)



