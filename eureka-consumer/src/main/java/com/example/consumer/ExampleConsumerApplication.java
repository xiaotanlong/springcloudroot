package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
//spring-cloud服务注册注解
@EnableDiscoveryClient
//SpringBoot启动注解
@SpringBootApplication
//自定义扫面的包
@ComponentScan(basePackages = {
		//"com.eureka.common.redis",
		//"com.eureka.common.rediscluster",
		//"com.eureka.common.redisOneNoPool",
		"com.example.consumer.*"
})
@EnableCaching//开启缓存
public class ExampleConsumerApplication {

	/**
	 * 使用第三方的组件
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {

		SpringApplication.run(ExampleConsumerApplication.class, args);
	}
}
