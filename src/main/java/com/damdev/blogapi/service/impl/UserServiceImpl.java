package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import com.damdev.blogapi.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
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
    Timestamp timestamp = new Timestamp(date.getTime());
    userInfo.setRegDate(timestamp);
    userInfo.setModifyDate(timestamp);

    UserInfo result = userRepo.save(userInfo);

    return result.toString();
  }


}
