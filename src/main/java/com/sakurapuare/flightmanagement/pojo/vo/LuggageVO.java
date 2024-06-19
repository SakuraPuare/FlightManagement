package com.sakurapuare.flightmanagement.pojo.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LuggageVO {

    private Long id;

    private Long userId;

    private String username;

    private Long orderId;

    private Double weight;

    private Long staffId;

    private String staffName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
