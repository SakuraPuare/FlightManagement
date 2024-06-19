package com.sakurapuare.flightmanagement.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TicketVO {
    private Long id;

    private Long airlineId;

    private Long flightId;

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

    private BigDecimal price;
}
