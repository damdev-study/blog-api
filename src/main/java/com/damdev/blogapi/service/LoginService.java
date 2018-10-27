package com.damdev.blogapi.service;

import org.json.simple.JSONObject;

import com.damdev.blogapi.domain.UserInfo;

public interface LoginService {
	
	public JSONObject login(String userId, String password) throws Exception;

}
