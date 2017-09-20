package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@RestController
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
class NewsServiceApplication {

	static void main(String[] args) {
		SpringApplication.run NewsServiceApplication, args
	}

}
