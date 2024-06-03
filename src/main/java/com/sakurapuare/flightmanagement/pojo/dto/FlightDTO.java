package com.sakurapuare.flightmanagement.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO {

    @Positive(message = "Airline ID must be positive")
    private Long airlineId;

    @NotEmpty(message = "Flight number cannot be empty")
    private String flightNumber;

    @Positive(message = "Capacity must be positive")
    private int capacity;

    @NotEmpty(message = "Departure city cannot be empty")
    private String departureCity;

    @NotEmpty(message = "Arrival city cannot be empty")
    private String arrivalCity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotEmpty(message = "Date of departure cannot be empty")
    private LocalDateTime dateOfDeparture;

    @Positive(message = "Estimated travel time must be positive")
    private int estimatedTravelTime;
}
