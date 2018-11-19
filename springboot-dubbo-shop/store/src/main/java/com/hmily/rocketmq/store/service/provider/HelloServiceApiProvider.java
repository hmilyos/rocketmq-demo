package com.hmily.rocketmq.store.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.hmily.rocketmq.store.api.HelloServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceApiProvider implements HelloServiceApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceApiProvider.class);

    @Override
    public String sayHello(String name) {
        LOGGER.info("name: {}", name);
        return "store service say: " + name + " hello";
    }
}
