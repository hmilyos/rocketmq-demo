package com.hmily.rocketmq.payseller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hmily.rocketmq.payseller.mapper")
@ComponentScan(basePackages = {"com.hmily.rocketmq.payseller.*", "com.hmily.rocketmq.payseller.config.*"})
public class MainConfig {

}
