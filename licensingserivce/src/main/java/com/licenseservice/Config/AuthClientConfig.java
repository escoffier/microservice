package com.licenseservice.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

@EnableOAuth2Client
@Configuration
public class AuthClientConfig {

    @Value("${oauth.resource:http://192.168.21.182:8091}")
    private String baseUrl;
    @Value("${oauth.authorize:http://192.168.21.182:8091/oauth/authorize}")
    private String authorizeUrl;
    @Value("${oauth.token:http://192.168.21.182:8091/oauth/token}")
    private String tokenUrl;

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = new ArrayList<String>(2);
        //scopes.add("write");
        //scopes.add("read");
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId("acme");
        resource.setClientSecret("acmesecret");
        resource.setGrantType("password");
        //resource.setScope(scopes);

        resource.setUsername("william.woodward");
        resource.setPassword("password2");

        return resource;
    }


    //AuthorizationCodeResourceDetails
    @Bean("oAuth2RestTemplate")
    @LoadBalanced
    //通过zuul网关访问organization服务，所以不需要负载均衡
    public OAuth2RestOperations getAuthRestTemplate(OAuth2ClientContext context, OAuth2ProtectedResourceDetails details){
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
        //return new OAuth2RestTemplate(details, context);
    }
}
