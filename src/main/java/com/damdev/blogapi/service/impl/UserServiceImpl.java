package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.domain.User;
import com.damdev.blogapi.repository.UserRepo;
import com.damdev.blogapi.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

  @Autowired
  UserRepo userRepo;

  @Override
  public void insertUser(User user) {
    userRepo.insert(user);
  }


}
