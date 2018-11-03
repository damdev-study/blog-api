package com.damdev.blogapi.interceptor;

import com.damdev.blogapi.error.RestException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = request.getHeader("Authorization");
    token = token.split(" ")[1];
    RestTemplate restTemplate = new RestTemplate();

    MultiValueMap<String, String> postParams = new LinkedMultiValueMap<String, String>();
    postParams.add("token", token);

    String requestUrl = request.getRequestURL().toString();
    String requestUri = request.getRequestURI();

    requestUrl = requestUrl.replace(requestUri, "");

    try {
      restTemplate.postForObject(requestUrl+"/oauth/check_token", postParams, JSONObject.class);
    } catch (HttpStatusCodeException e) {
      throw new RestException(Integer.parseInt(e.getStatusCode().toString()), e.getResponseBodyAsString());
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {

  }
}
