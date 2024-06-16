package com.sakurapuare.flightmanagement.pojo.vo;

import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

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
