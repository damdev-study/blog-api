package com.damdev.blogapi.domain;

import java.util.Date;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;

/**
 * Author : zenic
 * Created : 03/11/2018
 */
@Data
public class Posts {

  @Id
  @Generated
  private String id;

  private String type;

  private String title;

  private String content;

  private String status;

  private String regId;

  private Date regDate;

  private String modifyId;

  private Date modifyDate;
}
