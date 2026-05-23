package com.dev.userService.consumer;

import com.dev.userService.entities.UserInfoDto;
import com.dev.userService.repository.UserInfoRepository;
import com.dev.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
    private UserInfoRepository userInfoRepository;
    private UserService userService;

    @Autowired
    public UserConsumer(UserService userService,UserInfoRepository userInfoRepository) {
        this.userService=userService;
        this.userInfoRepository = userInfoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}")
    public void listen(UserInfoDto userInfoDto){
        try {
            System.out.println("Received user info: " + userInfoDto.toString());
            userService.createOrUpdateUser(userInfoDto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
