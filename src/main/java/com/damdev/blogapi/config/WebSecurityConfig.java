package com.damdev.blogapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Author : zenic Created : 05/10/2018
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // csrf 토큰 미사용 선언 - 토큰 미사용은 추천방법 아니므로 해결책이 될 수 없음
        //.httpBasic().and()
        .authorizeRequests()
        .antMatchers("/**", "/damdev/api/user").permitAll()  // url 에 권한 주기
        .anyRequest().authenticated();
        //.and().formLogin()
        //.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
        //.csrf().csrfTokenRepository(csrfTokenRepository());
  }

  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("X-CSRF-TOKEN");
    return repository;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
