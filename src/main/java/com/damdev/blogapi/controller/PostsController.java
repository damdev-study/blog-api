package com.damdev.blogapi.controller;

import com.damdev.blogapi.domain.Posts;
import com.damdev.blogapi.param.PostsParam;
import com.damdev.blogapi.service.PostsService;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zenic
 * Created : 03/11/2018
 */
@RestController
@RequestMapping(value = "/damdev/api/posts")
public class PostsController {

  @Resource
  PostsService postsService;

  @PostMapping
  public void regPost(Authentication authentication, PostsParam postsParam) {
    postsService.regPost(authentication, postsParam);
  }

  @GetMapping
  public Page<Posts> getPosts(PostsParam postsParam) {
    return postsService.getPosts(postsParam);
  }

  @PutMapping
  public void updatePost(Authentication authentication, PostsParam postsParam) {
    postsService.updatePost(authentication, postsParam);
  }

  @DeleteMapping
  public void deletePost(Authentication authentication, PostsParam postsParam) {
    postsService.deletePost(authentication, postsParam);
  }
}
