package com.oauthlogin.thirtyparty.repository;

import com.oauthlogin.thirtyparty.model.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthUserRepository extends JpaRepository<OAuthUser, Integer> {
    
    OAuthUser findByOAuthTypeAndOAuthId(String oAuthType, String oAuthId);

}
