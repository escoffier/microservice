package com.authenticationserivce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("eagleeye")
//                .secret("thisissecret")
//                .authorizedGrantTypes("refresh_token", "password", "authorization_code");
                //.scopes("webclient", "mobileclient");

        clients.inMemory()
                .withClient("acme")
                .secret("acmesecret")
                .authorizedGrantTypes("authorization_code", "refresh_token",
                        "password").scopes("openid");

    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //super.configure(endpoints);
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        //endpoints.authenticationManager(authenticationManager);
    }
}
