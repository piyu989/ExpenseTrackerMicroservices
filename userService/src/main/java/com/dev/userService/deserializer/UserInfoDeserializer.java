package com.dev.userService.deserializer;

import com.dev.userService.entities.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Map;

@Service
public class UserInfoDeserializer implements Deserializer<UserInfoDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public UserInfoDto deserialize(String s, byte[] bytes) {
        System.out.println("Deserializing user info..."+s);
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDto user = null;
        try {
            user = objectMapper.readValue(bytes, UserInfoDto.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
