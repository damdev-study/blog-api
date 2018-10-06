package com.damdev.blogapi.controller;

import com.damdev.blogapi.domain.User;
import com.damdev.blogapi.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/damdev/api/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping
  public void signUpUser(HttpServletRequest request, @RequestBody User user) {
    userService.insertUser(user);
  }
}
