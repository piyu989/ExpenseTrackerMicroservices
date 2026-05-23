package com.dev.userService.controller;

import com.dev.userService.entities.UserInfo;
import com.dev.userService.entities.UserInfoDto;
import com.dev.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/saveUser")
    public ResponseEntity<UserInfo> saveOrUpdateUser(@RequestBody UserInfoDto userInfo){
        try {
            UserInfo orUpdateUser = userService.createOrUpdateUser(userInfo);
            return ResponseEntity.ok(orUpdateUser);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/api/v1/getUser")
    public ResponseEntity<UserInfo> getUser(@RequestBody UserInfoDto userInfo){
        try {
            UserInfo orUpdateUser = userService.getUser(userInfo);
            System.out.println(orUpdateUser);
            return ResponseEntity.ok(orUpdateUser);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }



}
