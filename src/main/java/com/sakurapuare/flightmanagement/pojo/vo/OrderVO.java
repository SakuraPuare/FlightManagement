package com.sakurapuare.flightmanagement.pojo.vo;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class OrderVO {

    private Long id;

    private String airlineName;

    private String flightNumber;

    private String departureCity;

    private String arrivalCity;

    private LocalDateTime dateOfDeparture;

    private Integer estimatedTravelTime;

    private String seatClass;

    private String price;

    private String status = "unpaid";

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
