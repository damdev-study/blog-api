package com.damdev.blogapi.config;

import java.util.Arrays;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Author : zenic
 * Created : 20/10/2018
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;

  @Value("${application.name}")
  private String resourceId;

  @Value("${security.oauth2.client.accessTokenValiditySeconds}")
  private int accessTokenValiditySeconds;

  @Value("${security.oauth2.client.refreshTokenValiditySeconds}")
  private int refreshTokenValiditySeconds;

  @Value("${security.oauth2.client.clientId}")
  private String clientId;

  @Value("${security.oauth2.client.clientSecret}")
  private String clientSecret;

  @Value("${security.token.jwt.signingKey}")
  private String signingKey;

  @Autowired
  UserDetailServiceImpl userDetailServiceImpl;

  @Resource
  PasswordEncoder passwordEncoder;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .passwordEncoder(passwordEncoder)
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("permitAll()");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    log.info("start AuthorizationServerConfig AuthorizationServerEndpointsConfigurer configure");
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(
      Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter())
    );
    endpoints.tokenStore(tokenStore())
      .tokenEnhancer(tokenEnhancerChain)//추가한 token정보를 추가
      .userDetailsService(userDetailServiceImpl)
      .accessTokenConverter(jwtAccessTokenConverter())
      .authenticationManager(this.authenticationManager);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    log.info("start AuthorizationServerConfig ClientDetailsServiceConfigurer configure");
    clients.inMemory()
      .withClient(clientId)
      .authorizedGrantTypes("password", "refresh_token")
      .authorities("ROLE_USER")
      .scopes("read", "write")
      .resourceIds(resourceId)
      .accessTokenValiditySeconds(accessTokenValiditySeconds)
      .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
      .secret("{noop}" + clientSecret);
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    log.info("start AuthorizationServerConfig JwtAccessTokenConverter ");
    final JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
    accessTokenConverter.setSigningKey(signingKey);
    return accessTokenConverter;
  }

  @Bean
  public TokenStore tokenStore() {
    log.info("start AuthorizationServerConfig tokenStore ");
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public TokenEnhancer tokenEnhancer() {
    log.info("start AuthorizationServerConfig tokenEnhancer ");
    return new CustomTokenEnhancer();
  }
}

