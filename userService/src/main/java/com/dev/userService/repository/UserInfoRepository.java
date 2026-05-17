package com.dev.userService.repository;

import com.dev.userService.entities.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoDto, String> {

    UserInfoDto findByUserId(String userId);

}
