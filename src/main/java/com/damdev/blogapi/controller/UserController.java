package com.damdev.blogapi.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @PostMapping("/damdev/api/user")
  public JSONObject insertUser() {

  }
}
