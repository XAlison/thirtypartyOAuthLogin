/**
 * 
 */
package com.oauthlogin.thirtyparty.oauth.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;


public class LinkedInApi extends DefaultApi20 {
	
	private static final String AUTHORIZE_URL = "https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=%s&redirect_uri=%s&state=%s";
    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
    private static final String ACCESS_TOKEN_URL = 
    		"https://www.linkedin.com/uas/oauth2/accessToken?grant_type=authorization_code&code=%s&redirect_uri=%s&client_id=%s&client_secret=%s";
    
    private final String linkedInState;
    
    public LinkedInApi(String state){
    	this.linkedInState=state;
    }

	@Override
	public String getAccessTokenEndpoint() {
		return String.format(ACCESS_TOKEN_URL, linkedInState);
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		if (config.hasScope()){
			return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()),
					linkedInState, OAuthEncoder.encode(config.getScope()));
		}
		else{
			return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), linkedInState);
		}
	}

}
