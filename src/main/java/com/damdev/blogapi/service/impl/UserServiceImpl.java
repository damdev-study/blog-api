package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import com.damdev.blogapi.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Resource
  UserRepo userRepo;

  @Resource
  PasswordEncoder passwordEncoder;

  @Override
  public String insertUser(UserInfo userInfo) {

    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

    Date date = new Date();
    userInfo.setRegDate(date);
    userInfo.setModifyDate(date);
    userInfo.setRole("ROLE_USER");

    UserInfo result = userRepo.save(userInfo);

    return result.toString();
  }

  @Override
  public String updateUser(UserInfo userInfo) {
    UserInfo byId = userRepo.findById(userInfo.getId()).get();

    if(userInfo.getUserName() != null && !userInfo.getUserName().isEmpty()) {
      byId.setUserName(userInfo.getUserName());
    }

    if(userInfo.getStatus() != null && !userInfo.getStatus().isEmpty()) {
      byId.setStatus(userInfo.getStatus());
    }

    if(userInfo.getRole() != null && !userInfo.getRole().isEmpty()) {
      byId.setRole(userInfo.getRole());
    }

    if(userInfo.getPassword() != null && !userInfo.getPassword().isEmpty()) {
      byId.setPassword(passwordEncoder.encode(userInfo.getPassword()));
    }

    Date date = new Date();
    byId.setModifyDate(date);

    UserInfo result = userRepo.save(byId);

    return result.toString();
  }

}
