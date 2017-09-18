package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import java.security.Principal

@EnableWebMvc
@RestController
@RefreshScope
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
@EnableResourceServer
class NewsServiceApplication extends ResourceServerConfigurerAdapter {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	def get(Principal principal) {
		return "hello from news service" + principal.name
	}

	static void main(String[] args) {
		SpringApplication.run NewsServiceApplication, args
	}

	@Override
	void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
	}
}
