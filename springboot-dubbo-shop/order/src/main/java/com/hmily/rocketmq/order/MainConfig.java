package com.hmily.rocketmq.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hmily.rocketmq.order.mapper")
@ComponentScan(basePackages = {"com.hmily.rocketmq.order.*", "com.hmily.rocketmq.order.config.*"})
public class MainConfig {

}
