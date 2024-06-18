package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightVO {

    private Long id;

    private Long airlineId;

    private Long userId;

    private String airlineName;

    private String airlineCountry;

    private String airlineCode;

    private String airlineDescription;

    private String flightNumber;

    private Integer capacity;

    private String departureCity;

    private String arrivalCity;

    private LocalDateTime dateOfDeparture;

    private Integer estimatedTravelTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
