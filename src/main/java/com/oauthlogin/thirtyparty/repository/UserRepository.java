package com.oauthlogin.thirtyparty.repository;


import com.oauthlogin.thirtyparty.model.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<OAuthUser, Integer> {

    OAuthUser findByUsername(String username);

}
