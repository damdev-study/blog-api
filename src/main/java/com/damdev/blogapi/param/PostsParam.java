package com.damdev.blogapi.param;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Author : zenic
 * Created : 03/11/2018
 */
@Data
@Component
public class PostsParam {

  private String id;
  private String type;
  private String title;
  private String content;
  private String status;

  // List
  private String searchType;
  private String searchValue;
  private String currentPage;
  private String contentCount;

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
