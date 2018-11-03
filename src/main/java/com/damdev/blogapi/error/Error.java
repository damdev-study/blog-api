package com.damdev.blogapi.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

  private int resultCode;
  private String resultMsg;

}
