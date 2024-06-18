package com.sakurapuare.flightmanagement.pojo.vo;

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

    private String createdAt;

    private String updatedAt;
}
