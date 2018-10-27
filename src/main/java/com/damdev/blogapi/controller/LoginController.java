package com.damdev.blogapi.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.damdev.blogapi.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/damdev/api/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public JSONObject login(@RequestParam String userId, @RequestParam String password) {
		JSONObject resultObj = null;
		
		try {
			resultObj = loginService.login(userId, password);
		} catch (Exception e) {
			log.error("로그인 중 오류가 발생하였습니다.-");
			resultObj = new JSONObject();
			resultObj.put("result", "error");
		}
		
		return resultObj;
	}
}
