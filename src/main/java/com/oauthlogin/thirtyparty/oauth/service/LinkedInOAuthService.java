/**
 * 
 */
package com.oauthlogin.thirtyparty.oauth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.oauthlogin.thirtyparty.config.OAuthTypes;
import com.oauthlogin.thirtyparty.model.OAuthUser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;


public class LinkedInOAuthService extends OAuthServiceDeractor {
	
	private static final String PROTECTED_RESOURCE_URL = "https://api.linkedin.com/v1/people/~?format=json";

	public LinkedInOAuthService(OAuthService oAuthService) {
		super(oAuthService, OAuthTypes.LINKEDIN);
	}

	/* (non-Javadoc)
	 * @see com.jeeplus.oauth.service.OAuthServiceDeractor#getOAuthUser(org.scribe.model.Token)
	 */
	@Override
	public OAuthUser getOAuthUser(Token accessToken) {
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        this.signRequest(accessToken, request);
        Response response = request.send();
        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.setoAuthType(getoAuthType());
        Object result = JSON.parse(response.getBody());
        oAuthUser.setoAuthId(JSONPath.eval(result, "$.id").toString());
        //oAuthUser.setUser(new User());
       // oAuthUser.getUser().setUsername(JSONPath.eval(result, "$.login").toString());
        return oAuthUser;
	}

}
