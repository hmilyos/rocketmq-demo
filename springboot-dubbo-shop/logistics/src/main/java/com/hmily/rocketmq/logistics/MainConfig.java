package com.hmily.rocketmq.logistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hmily.rocketmq.logistics.mapper")
@ComponentScan(basePackages = {"com.hmily.rocketmq.logistics.*", "com.hmily.rocketmq.logistics.config.*"})
public class MainConfig {

}
