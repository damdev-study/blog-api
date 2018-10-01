package com.damdev.blogapi.domain;

import java.sql.Timestamp;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

  @Id
  private int id;

  private String userId;

  private String userName;

  private String password;

  private Timestamp regDate;

  private Timestamp modifyDate;

  private String status;

  private String roll;
}
