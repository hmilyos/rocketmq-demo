package com.hmily.rocketmq.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hmily.rocketmq.store.mapper")
@ComponentScan(basePackages = {"com.hmily.rocketmq.store.*", "com.hmily.rocketmq.store.config.*"})
public class MainConfig {

}
