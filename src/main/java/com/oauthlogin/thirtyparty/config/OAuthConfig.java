package com.oauthlogin.thirtyparty.config;

import com.oauthlogin.thirtyparty.oauth.api.GithubApi;
import com.oauthlogin.thirtyparty.oauth.api.LinkedInApi;
import com.oauthlogin.thirtyparty.oauth.service.GithubOAuthService;
import com.oauthlogin.thirtyparty.oauth.service.LinkedInOAuthService;
import com.oauthlogin.thirtyparty.oauth.service.OAuthServiceDeractor;
import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthConfig {
    
    private static final String CALLBACK_URL = "%s/oauth/%s/callback";

    @Value("${oAuth.github.state}") String githubState;
    @Value("${oAuth.github.clientId}") String githubClientId;
    @Value("${oAuth.github.clientSecret}") String githubClientSecret;
    @Value("${demo.host}") String host;


    @Value("${oAuth.linkedin.state}") String linkedinState;
    @Value("${oAuth.linkedin.clientId}") String linkedinClientId;
    @Value("${oAuth.linkedin.clientSecret}") String linkedinClientSecret;
    @Value("${oAuth.linkedin.scope}") String scope;
    
    @Bean
    public GithubApi githubApi(){
        return new GithubApi(githubState);
    }
    
    @Bean
    public OAuthServiceDeractor getGithubOAuthService(){
        return new GithubOAuthService(new ServiceBuilder()
            .provider(githubApi())
            .apiKey(githubClientId)
            .apiSecret(githubClientSecret)
            .callback(String.format(CALLBACK_URL, host, OAuthTypes.GITHUB))
            .build());
    }

    @Bean
    public LinkedInApi linkedInApi(){
        return new LinkedInApi(linkedinState);
    }
    @Bean
    public OAuthServiceDeractor getLinkedInOAuthService(){
        return new LinkedInOAuthService(new ServiceBuilder()
                .provider(linkedInApi())
                .apiKey(linkedinClientId)
                .apiSecret(linkedinClientSecret)
                .scope(scope)
                .callback(String.format(CALLBACK_URL, host, OAuthTypes.LINKEDIN))
                .build());
    }

}
