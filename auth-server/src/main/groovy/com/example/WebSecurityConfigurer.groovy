package com.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * @author Aydar Farrakhov 
 * @since 18.09.2017
 */
@Configuration
class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean()
    }

    @Override
    @Bean
    UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean()
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password1").roles("USER")
                .and()
                .withUser("admin").password("password2").roles("USER", "ADMIN")
    }
}
