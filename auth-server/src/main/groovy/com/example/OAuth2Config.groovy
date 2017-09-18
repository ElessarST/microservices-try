package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
/**
 * @author Aydar Farrakhov 
 * @since 18.09.2017
 */
@Configuration
class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager

    @Autowired
    private UserDetailsService userDetailsService

    @Override
    void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app")
                .secret("secret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient")
    }


    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
    }
}
