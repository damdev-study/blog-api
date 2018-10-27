package com.damdev.blogapi.service;

import org.json.simple.JSONObject;

public interface LoginService {
	
	public JSONObject login(String userId, String password) throws Exception;

}
