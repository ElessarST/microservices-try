package com.example

import feign.RequestInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextListener
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import java.security.Principal

@EnableWebMvc
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@EnableResourceServer
@EnableConfigurationProperties
@Configuration
@EnableOAuth2Client
class FamilyServiceApplication {

	@Autowired
    NewsService newsService

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	def get(Principal principal) {
		return newsService.iFailSometimes(principal.name)
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	def getFailed() {
		return newsService.iFailSometimes()
	}

	@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails()
	}

	@Bean
	RequestInterceptor oauth2FeignRequestInterceptor(){
		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails())
	}

	@Bean
	OAuth2RestTemplate clientCredentialsRestTemplate() {
		return new OAuth2RestTemplate(clientCredentialsResourceDetails())
	}

	@Bean
	RequestContextListener requestContextListener() {
		return new RequestContextListener()
	}

	static void main(String[] args) {
		SpringApplication.run FamilyServiceApplication, args
	}
}
