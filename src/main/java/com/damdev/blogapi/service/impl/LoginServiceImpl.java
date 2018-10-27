package com.damdev.blogapi.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.damdev.blogapi.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService{

	  @Value("${security.oauth2.client.clientId}")
	  private String clientId;
	
	  @Value("${security.oauth2.client.clientSecret}")
	  private String clientSecret;
	
	@Override
	public JSONObject login(String userId, String password) throws Exception{
		JSONObject resultObj = new JSONObject();
		
		if(userId != null && password != null && !userId.equals("") && !password.equals("") ) {
			
			log.debug("parameter : username=" +userId + ", password=" + password) ;

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
			parameters.add("username", userId);
			parameters.add("password", password);
			parameters.add("grant_type", "password");

			RestTemplate templete = new RestTemplateBuilder().basicAuthorization(clientId, clientSecret).build();
			try {
				resultObj = templete.postForObject("http://127.0.0.1:8080/oauth/token", parameters, JSONObject.class);
			} catch (HttpStatusCodeException e) {
				if(e.getStatusCode().is5xxServerError()) {
					resultObj.put("error", "server internal error");
					log.error("/oauth/token "+e.getStatusCode()+" error:" + e.getResponseBodyAsString());
				}
				else {
					resultObj = (JSONObject) new JSONParser().parse(e.getResponseBodyAsString());
					log.error("/oauth/token "+e.getStatusCode()+" error:"+e.getResponseBodyAsString());
				}
			}
			
		}
		else {
			log.error( "[LoginService-login] 올바르지 않은 파라미터입니다. - userId is null:"+ (userId==null) + ", password is null:" + (password==null) );
			resultObj.put("error", "invalid parameter");
		}
		
		return resultObj;
	}

}
