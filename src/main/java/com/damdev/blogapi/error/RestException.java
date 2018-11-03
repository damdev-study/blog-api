package com.damdev.blogapi.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestException extends RuntimeException {
  private int errCode;
  private String errMsg;
}
