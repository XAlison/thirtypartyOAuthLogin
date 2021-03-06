package com.oauthlogin.thirtyparty.oauth.service;

import com.oauthlogin.thirtyparty.model.OAuthUser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public abstract class OAuthServiceDeractor implements OAuthService {

    private final OAuthService oAuthService;
    private final String oAuthType;
    private final String authorizationUrl;
    
    public OAuthServiceDeractor(OAuthService oAuthService, String type) {
        super();
        this.oAuthService = oAuthService;
        this.oAuthType = type;
        this.authorizationUrl = oAuthService.getAuthorizationUrl(null);
    }


    public Token getRequestToken() {
        return oAuthService.getRequestToken();
    }


    public Token getAccessToken(Token requestToken, Verifier verifier) {
        return oAuthService.getAccessToken(requestToken, verifier);
    }


    public void signRequest(Token accessToken, OAuthRequest request) {
        oAuthService.signRequest(accessToken, request);
    }


    public String getVersion() {
        return oAuthService.getVersion();
    }


    public String getAuthorizationUrl(Token requestToken) {
        return oAuthService.getAuthorizationUrl(requestToken);
    }

    public String getoAuthType() {
        return oAuthType;
    }
    
    public String getAuthorizationUrl(){
        return authorizationUrl;
    }
    
    public abstract OAuthUser getOAuthUser(Token accessToken);

}
