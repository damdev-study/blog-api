package com.damdev.blogapi.controller;

import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/damdev/api/signup")
  public void signUpUser(HttpServletResponse response, HttpServletRequest request, @RequestBody UserInfo userInfo) {
    log.debug("signUpUser");
    userService.insertUser(userInfo);
  }

  @PutMapping("/damdev/api/user")
  public void modifyUser(HttpServletResponse response, HttpServletRequest request, @RequestBody UserInfo userInfo) {
    log.debug("modifyUser");
    userService.updateUser(userInfo);
  }
}
