package com.damdev.blogapi.repository;

import com.damdev.blogapi.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

}
