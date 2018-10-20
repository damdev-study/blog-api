package com.damdev.blogapi.domain;

import java.sql.Timestamp;
import java.util.Date;
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

  private Date regDate;

  private Date modifyDate;

  private String status;

  private String roll;
}
