package com.damdev.blogapi.config;

import com.damdev.blogapi.domain.UserInfo;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author : zenic
 * Created : 20/10/2018
 */
public class CustomUserDetails implements UserDetails {

  private String username;
  private String password;
  Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(UserInfo userInfo) {
    this.username = userInfo.getUserId();
    this.password = userInfo.getPassword();
    this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
