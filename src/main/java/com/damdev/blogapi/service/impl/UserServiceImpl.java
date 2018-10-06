package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import com.damdev.blogapi.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepo userRepo;

  @Override
  public void insertUser(UserInfo user) {

    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());
    user.setRegDate(timestamp);
    user.setModifyDate(timestamp);

    userRepo.insert(user);
  }


}
