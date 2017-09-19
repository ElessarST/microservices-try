package com.example

import feign.RequestInterceptor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextListener
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import java.security.Principal

@EnableWebMvc
@RestController
@EnableOAuth2Client
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
@EnableResourceServer
@EnableConfigurationProperties
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class NewsServiceApplication extends ResourceServerConfigurerAdapter {


	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	def get(@PathVariable String name, Principal principal) {
		return "hello from news service ${name} ${principal.name}"
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	def get(Principal principal) {
		return "hello from news service ${principal.name}"
	}

	static void main(String[] args) {
		SpringApplication.run NewsServiceApplication, args
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

	@Bean
	TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter())
	}

	@Bean
	JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter()
		converter.setSigningKey("123")
		return converter
	}

	@Bean
	@Primary
	DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices()
		defaultTokenServices.setTokenStore(tokenStore())
		defaultTokenServices.setSupportRefreshToken(true)
		return defaultTokenServices
	}

	@Override
	void configure(ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices())
	}

	@Override
	void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
	}
}
