package com.damdev.blogapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

  @Id
  private String id;
  private String userId;
  private String name;
  private String pass;
}
