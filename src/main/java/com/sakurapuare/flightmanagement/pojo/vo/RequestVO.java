package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestVO {

    private Long id;

    private Long userId;

    private String username;

    private String info;

    private String status = "unhandled";

    private Long handlerId;

    private String handlerName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
