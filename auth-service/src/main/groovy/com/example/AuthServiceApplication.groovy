package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@ComponentScan
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AuthServiceApplication {


	static void main(String[] args) {
		SpringApplication.run AuthServiceApplication, args
	}

	@Configuration
	@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
	class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

		@Autowired
		DumUserDetailsService userDetailsService

		@Override
		@Bean
		AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean()
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.anyRequest().authenticated()
					.and()
					.csrf().disable()
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService)
		}
	}

	@EnableAuthorizationServer
	@Configuration
	class OAuth2Config extends AuthorizationServerConfigurerAdapter {


		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager

		@Autowired
		private DumUserDetailsService userDetailsService

		@Override
		void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("app")
					.secret("secret")
					.authorizedGrantTypes("refresh_token", "password", "client_credentials")
					.scopes("webclient", "mobileclient")
			.and()
					.withClient("server")
					.secret("secret")
					.authorizedGrantTypes("refresh_token", "password", "client_credentials")
					.scopes("server")
		}


		@Override
		void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
					.tokenStore(tokenStore())
					.accessTokenConverter(accessTokenConverter())
					.authenticationManager(authenticationManager)
					.userDetailsService(userDetailsService)
		}

		@Override
		void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer
					.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()")
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

	}

}