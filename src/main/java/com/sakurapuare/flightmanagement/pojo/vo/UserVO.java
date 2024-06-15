package com.sakurapuare.flightmanagement.pojo.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserVO {
    private Long userId;

    private String username;

    private String email;

    private Integer role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
