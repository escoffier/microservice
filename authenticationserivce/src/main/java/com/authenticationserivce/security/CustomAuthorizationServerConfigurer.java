package com.authenticationserivce.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.stereotype.Component;

//@Component
public class CustomAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    AuthenticationManager authenticationManager;

    public CustomAuthorizationServerConfigurer(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        this.authenticationManager =
                authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void configure(
            ClientDetailsServiceConfigurer clients
    ) throws Exception {
        clients.inMemory()
                .withClient("client")
                .authorizedGrantTypes("password")
                .secret("secret")
                .scopes("all");
    }

    @Override
    public void configure(
            AuthorizationServerEndpointsConfigurer endpoints
    ) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
