package com.dev.userService.repository;

import com.dev.userService.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUsername(String username);

}
