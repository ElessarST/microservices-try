package com.example.config.security.security

import com.example.user.DumUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * @author Aydar Farrakhov 
 * @since 20.09.2017
 */
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
