package com.dev.userService.service;

import com.dev.userService.entities.UserInfo;
import com.dev.userService.entities.UserInfoDto;
import com.dev.userService.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserService {

    private UserInfoRepository userInfoRepository;

    @Autowired
    UserService(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo createOrUpdateUser(UserInfoDto dto){
        Function<UserInfo,UserInfo> updateUserInfo = (u) -> {
            u.setName(dto.getName());
            u.setUsername(dto.getUsername());
            u.setPassword(dto.getPassword());
            u.setMobile(dto.getMobile());
            u.setProfile(dto.getProfile());
            u.setLastName(dto.getLastName());
            return u;
        };

        Supplier<UserInfo> userInfoSupplier = () -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(dto.getUserId());
            userInfo.setName(dto.getName());
            userInfo.setUsername(dto.getUsername());
            userInfo.setPassword(dto.getPassword());
            userInfo.setMobile(dto.getMobile());
            userInfo.setProfile(dto.getProfile());
            userInfo.setLastName(dto.getLastName());
            return userInfo;
        };

        UserInfo userInfo = userInfoRepository.findByUsername(dto.getUsername())
                .map(updateUserInfo)
                .orElseGet(userInfoSupplier);

        return userInfoRepository.save(userInfo);
    }

    public UserInfo getUser(UserInfoDto userInfoDto){
        return userInfoRepository.findByUsername(userInfoDto.getUsername())
                .orElse(null);
    }
}
