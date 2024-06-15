package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.sakurapuare.flightmanagement.pojo.entity.user.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginVO extends UserVO {
    String token;
    LocalDateTime expireTime;

    public UserLoginVO(User user, String token) {
        BeanUtils.copyProperties(user, this);
        this.token = token;
        expireTime = LocalDateTime.now().plusDays(1);
    }
}
