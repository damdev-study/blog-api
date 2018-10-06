package com.damdev.blogapi.config;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author : zenic
 * Created : 2018. 9. 29.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Resource
  UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    Optional<UserInfo> byUserId = userRepo.findByUserId(userId);
    UserInfo userInfo = byUserId.orElseThrow(() -> new UsernameNotFoundException(userId));

    return new User(userInfo.getUserId(), userInfo.getPassword(), authorities());
  }

  private Collection<? extends GrantedAuthority> authorities() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }
}
