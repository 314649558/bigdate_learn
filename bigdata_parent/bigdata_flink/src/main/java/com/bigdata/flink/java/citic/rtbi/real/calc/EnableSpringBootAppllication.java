package com.bigdata.flink.java.citic.rtbi.real.calc;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootConfiguration
@ComponentScan(basePackages = {""})
@Import({

})
public @interface EnableSpringBootAppllication {
}
