package com.damdev.blogapi.config;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * Author : zenic
 * Created : 20/10/2018
 */
@Slf4j
@Configuration
public class CustomTokenEnhancer implements TokenEnhancer {

  @Resource
  UserRepo userRepo;

  @Override
  public OAuth2AccessToken enhance(
    OAuth2AccessToken accessToken,
    OAuth2Authentication authentication) {

    log.info("토큰 생성");

    Map<String, Object> additionalInfo = new HashMap<>();
    // CustomUserDetails 에서 정보를 가지고 온다
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
    String userId = user.getUsername();
    UserInfo userInfo = userRepo.findByUserId(userId).get();

    // 여기서 필요한 정보 추가
    additionalInfo.put("id", userInfo.getId());
    additionalInfo.put("userId", userId);
    additionalInfo.put("status", userInfo.getStatus());
    additionalInfo.put("role", userInfo.getRole());

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }
}
