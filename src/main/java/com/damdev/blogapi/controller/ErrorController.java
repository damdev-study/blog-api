package com.damdev.blogapi.controller;

import com.damdev.blogapi.error.Error;
import com.damdev.blogapi.error.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorController {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(RestException.class)
  public Error handleRestException(RestException re) {
    return new Error(re.getErrCode(), re.getErrMsg());
  }

}
