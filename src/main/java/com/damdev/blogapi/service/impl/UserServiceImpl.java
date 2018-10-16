package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import com.damdev.blogapi.service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Resource
  UserRepo userRepo;

  @Override
  public void insertUser(UserInfo userInfo) {

    Date date = new Date();
    Timestamp timestamp = new Timestamp(date.getTime());
    userInfo.setRegDate(timestamp);
    userInfo.setModifyDate(timestamp);

    userRepo.save(userInfo);
  }


}
