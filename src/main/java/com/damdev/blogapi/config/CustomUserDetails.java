package com.damdev.blogapi.config;

import com.damdev.blogapi.domain.UserInfo;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author : zenic
 * Created : 20/10/2018
 */
public class CustomUserDetails implements UserDetails {

  private String id;
  private String username;
  private String password;
  Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(UserInfo userInfo, Collection<? extends GrantedAuthority> authorities) {
    this.id = userInfo.getId();
    this.username = userInfo.getUserId();
    this.password = userInfo.getPassword();
    this.authorities = authorities;
  }

  public String getId() {
    return this.id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
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
