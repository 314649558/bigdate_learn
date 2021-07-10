package com.hailong.sharding.jdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hailong.sharding.jdbc.entity.Course;
import com.hailong.sharding.jdbc.mapper.CourseMapper;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author yuanhailong
 * @date 2021/7/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcApplicationTest {

    @Autowired
    private CourseMapper courseMapper;


    @Test
    public void addCourse(){
        for (long i=0;i<100;i++) {
            Course course = new Course();
            course.setCname("java");
            course.setCstatus("1");
            course.setUserId((i+1));
            courseMapper.insert(course);
        }
    }

    @Test
    public void queryCourse(){
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("cid",0);
        Course course = courseMapper.selectOne(queryWrapper);
        System.out.println(course);
    }

}
