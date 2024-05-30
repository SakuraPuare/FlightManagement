package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLoginVO {
    String token;
    LocalDateTime expireTime;

    public UserLoginVO(String token) {
        this.token = token;
        expireTime = LocalDateTime.now().plusDays(1);
    }
}
