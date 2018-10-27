package com.damdev.blogapi;

import com.damdev.blogapi.config.CustomUserDetails;
import com.damdev.blogapi.domain.UserInfo;
import com.damdev.blogapi.repository.UserRepo;
import java.util.Optional;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {

  @Resource
  UserRepo userRepo;

  @Resource
  PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(Application.class);
    application.run(args);
  }

  @Autowired
  public void authenticationManager(AuthenticationManagerBuilder builder, UserRepo userRepo)
    throws Exception {
    builder
      .userDetailsService(s -> new CustomUserDetails(userRepo.findByUserId(s).get()));
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Optional<UserInfo> byUserId = userRepo.findByUserId("admin");
    UserInfo user = byUserId.orElseGet(() -> {
      UserInfo addUser = new UserInfo();
      addUser.setUserId("admin");
      addUser.setPassword(passwordEncoder.encode("1234"));
      addUser.setUserName("관리자");
      addUser.setRole("ROLE_ADMIN");

      return userRepo.save(addUser);
    });

    log.info(user.toString());
  }
}
