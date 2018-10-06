package com.damdev.blogapi.repository;

import com.damdev.blogapi.domain.UserInfo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserInfo, String> {

  Optional<UserInfo> findByUserId(String userId);
}
