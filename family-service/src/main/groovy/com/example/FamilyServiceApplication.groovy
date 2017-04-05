package com.example

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
class FamilyServiceApplication {

	@Value('${message:message}')
	String message

	@RequestMapping(value = "/", method = RequestMethod.GET)
	def get() {
		return ['message': message]
	}

	static void main(String[] args) {
		SpringApplication.run FamilyServiceApplication, args
	}
}
