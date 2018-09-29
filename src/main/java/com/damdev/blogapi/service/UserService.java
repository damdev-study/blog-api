package com.damdev.blogapi.service;

import com.damdev.blogapi.domain.User;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  void insertUser(User user);
}
