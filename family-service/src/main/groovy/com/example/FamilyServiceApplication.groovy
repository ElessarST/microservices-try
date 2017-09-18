package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
class FamilyServiceApplication {

	@Value('${message:message}')
	String message

	@Autowired
    NewsService newsService

	@RequestMapping(value = "/", method = RequestMethod.GET)
	def get() {
		return newsService.iFailSometimes()
	}

	static void main(String[] args) {
		SpringApplication.run FamilyServiceApplication, args
	}
}
