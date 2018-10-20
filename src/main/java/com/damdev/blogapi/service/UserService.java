package com.damdev.blogapi.service;

import com.damdev.blogapi.domain.UserInfo;
import java.util.Optional;

public interface UserService {
  String insertUser(UserInfo userInfo);

  String updateUser(UserInfo userInfo);

}
