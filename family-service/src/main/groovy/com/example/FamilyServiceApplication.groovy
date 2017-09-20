package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
class FamilyServiceApplication {

	static void main(String[] args) {
		SpringApplication.run FamilyServiceApplication, args
	}
}
