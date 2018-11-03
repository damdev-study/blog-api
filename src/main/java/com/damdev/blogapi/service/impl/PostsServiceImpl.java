package com.damdev.blogapi.service.impl;

import com.damdev.blogapi.config.CustomUserDetails;
import com.damdev.blogapi.domain.Posts;
import com.damdev.blogapi.param.PostsParam;
import com.damdev.blogapi.repository.PostsRepository;
import com.damdev.blogapi.service.PostsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Author : zenic
 * Created : 03/11/2018
 */
@Slf4j
@Service
public class PostsServiceImpl implements PostsService {

  @Resource
  PostsRepository postsRepo;

  @Override
  public void regPost(Authentication authentication, PostsParam postsParam) {

    CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
    String userObjectId = principal.getId();

    ObjectMapper mapper = new ObjectMapper();

    try {
      Posts posts = mapper.readValue(postsParam.toString(), Posts.class);

      posts.setRegId(userObjectId);
      posts.setRegDate(new Date());
      posts.setModifyId(userObjectId);
      posts.setModifyDate(new Date());

      log.info("글 등록 : " + posts);

      postsRepo.save(posts);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Page<Posts> getPosts(PostsParam postsParam) {
    return postsRepo
      .findByTitleContainsOrContentContains(postsParam.getSearchValue(),
        PageRequest.of(postsParam.getCurrentPage(), postsParam.getContentCount()));
  }

  @Override
  public void updatePost(Authentication authentication, PostsParam postsParam) {

    CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

    Optional<Posts> byId = postsRepo.findById(postsParam.getId());
    Posts posts = byId.orElseThrow(() -> new IllegalArgumentException());

    posts.setType(postsParam.getType());
    posts.setTitle(postsParam.getTitle());
    posts.setContent(postsParam.getContent());
    posts.setStatus(postsParam.getStatus());
    posts.setModifyId(principal.getId());
    posts.setModifyDate(new Date());

    log.info("글 수정 : " + posts);

    postsRepo.save(posts);
  }

  @Override
  public void deletePost(Authentication authentication, PostsParam postsParam) {

    CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

    Optional<Posts> byId = postsRepo.findById(postsParam.getId());
    Posts posts = byId.orElseThrow(() -> new IllegalArgumentException());

    posts.setModifyId(principal.getId());
    posts.setModifyDate(new Date());

    log.info("글 삭제 : " + posts);

    postsRepo.delete(posts);
  }
}
