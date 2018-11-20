package com.hmily.rocketmq.paycustomer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hmily.rocketmq.paycustomer.mapper")
@ComponentScan(basePackages = {"com.hmily.rocketmq.paycustomer.*", "com.hmily.rocketmq.paycustomer.config.*"})
public class MainConfig {

}
