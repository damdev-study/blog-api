package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.domain.User;
import com.damdev.blogapi.repository.UserRepo;
import com.damdev.blogapi.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

  @Autowired
  UserRepo userRepo;

  @Override
  public void insertUser(User user) {

    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());
    user.setRegDate(timestamp);
    user.setModifyDate(timestamp);

    userRepo.insert(user);
  }


}
