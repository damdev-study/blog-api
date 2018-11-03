package com.damdev.blogapi.repository;

import com.damdev.blogapi.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author : zenic
 * Created : 03/11/2018
 */
public interface PostsRepository extends MongoRepository<Posts, String> {

  Page<Posts> findByTitleContainsOrContentContains(String searchValue, Pageable pageable);
}
