package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
@EnableOAuth2Sso
class GatewayApplication {

	static void main(String[] args) {
		SpringApplication.run GatewayApplication, args
	}
}
