package com.damdev.blogapi.domain;

import java.sql.Timestamp;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;

@Data
public class UserInfo {

  @Id
  @Generated
  private String id;

  private String userId;

  private String userName;

  private String password;

  private Timestamp regDate;

  private Timestamp modifyDate;

  private String status;

  private String roll;
}
