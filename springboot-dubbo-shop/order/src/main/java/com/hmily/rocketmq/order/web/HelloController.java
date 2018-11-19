package com.hmily.rocketmq.order.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hmily.rocketmq.store.api.HelloServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.hmily.rocketmq.store.api.HelloServiceApi",
            check = false,
            timeout = 1000,
            retries = 0
    )
    private HelloServiceApi helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        LOGGER.info("name: {}", name);
        return helloService.sayHello(name);
//        return "hello order";
    }
}
