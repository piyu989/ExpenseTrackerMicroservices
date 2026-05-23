package com.dev.userService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDto {
    private long userId;
    private String name;
    private String lastName;
    private String password;
    @JsonProperty("phone_no")
    private String mobile;
    @JsonProperty("username")
    private String username;
    private String profile;

    UserInfo transformToUserInfo(){
        return UserInfo.builder()
                .userId(this.userId)
                .name(this.name)
                .lastName(this.lastName)
                .password(this.password)
                .mobile((this.mobile))
                .username(this.username)
                .profile(this.profile)
                .build();
    }


}
