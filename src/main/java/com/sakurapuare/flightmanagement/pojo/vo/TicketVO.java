package com.sakurapuare.flightmanagement.pojo.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TicketVO {
    private Long id;

    private String airlineName;

    private String airlineCountry;

    private String flightNumber;

    private Integer capacity;

    private String departureCity;

    private String arrivalCity;

    private LocalDateTime dateOfDeparture;

    private Integer estimatedTravelTime;

    private String seatClass;

    private Long quota;

    private String price;
}
