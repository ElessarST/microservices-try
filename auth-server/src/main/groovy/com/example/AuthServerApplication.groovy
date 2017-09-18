package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
@RefreshScope
class AuthServerApplication {

	@RequestMapping(value =  "/user", produces = "application/json")
	Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>()
		userInfo.put("user", user.getUserAuthentication().getPrincipal())
		return userInfo
	}

	static void main(String[] args) {
		SpringApplication.run AuthServerApplication, args
	}
}
