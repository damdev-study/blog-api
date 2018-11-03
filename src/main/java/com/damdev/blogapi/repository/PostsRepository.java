package com.damdev.blogapi.repository;

import com.damdev.blogapi.domain.Posts;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author : zenic
 * Created : 03/11/2018
 */
public interface PostsRepository extends MongoRepository<Posts, String> {

  List<Posts> findByTitleContainsOrContentContains(String searchValue);
}
