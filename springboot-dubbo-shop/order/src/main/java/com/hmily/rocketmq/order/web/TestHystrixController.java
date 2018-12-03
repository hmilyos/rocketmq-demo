package com.hmily.rocketmq.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class TestHystrixController {
	
	private static final Logger log = LoggerFactory.getLogger(TestHystrixController.class);

	@HystrixCommand(commandKey = "testTimeout", 
			commandProperties = {
					@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
			}, 
			fallbackMethod = "createOrderFallbackMethod4Timeout"
	)
	@RequestMapping("/testTimeout")
	public String testTimeout(@RequestParam("cityId") String cityId) throws Exception {
		return "超时降级";
	}
	public String createOrderFallbackMethod4Timeout(@RequestParam("cityId") String cityId) throws Exception {
		log.info("-------超时降级策略执行 cityId: {} ", cityId);
		return "hysrtix timeout !";
	}
	

	@HystrixCommand(commandKey = "testQueueSize", 
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD") }, threadPoolKey = "testQueueSizeThreadPool", threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "20000"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "30") 
			}, 
			fallbackMethod = "createOrderFallbackMethod4Thread"
	)
	@RequestMapping("/testQueueSize")
	public String testQueueSize(@RequestParam("cityId") String cityId) throws Exception {
		return "线程池方式降级";
	}
	public String createOrderFallbackMethod4Thread(@RequestParam("cityId") String cityId) throws Exception {
		log.info("-------线程池限流降级策略执行 cityId: {} ", cityId);
		return "hysrtix threadpool !";
	}

	
	@HystrixCommand(commandKey = "testSemaphore", 
			commandProperties = {
				@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
				@HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "3") 
			}, 
			fallbackMethod = "createOrderFallbackMethod4semaphore"
	)
	@RequestMapping("/testSemaphore")
	public String testSemaphore(@RequestParam("cityId") String cityId) throws Exception {
		return "线程池方式降级";
	}
	public String createOrderFallbackMethod4semaphore(@RequestParam("cityId") String cityId) throws Exception {
		log.info("-------信号量限流降级策略执行 cityId: {} ", cityId);
		return "hysrtix semaphore !";
	}
}
