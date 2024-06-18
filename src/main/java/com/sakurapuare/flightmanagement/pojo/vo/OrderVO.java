package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderVO {

    private Long id;

    private Long airlineId;

    private Long flightId;

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
