package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TicketDTO {

    @Positive(message = "Flight ID must be positive")
    private Long flightId;

    @Positive(message = "Passenger ID must be positive")
    private Long passengerId;

    @NotEmpty(message = "Seat class must not be empty")
    private String seatClass;

    @NotEmpty(message = "Seat number must not be empty")
    private String seatNumber;

    @NotEmpty(message = "Status must not be empty")
    private String status;

    @NotEmpty(message = "Price must not be empty")
    private String price;
}
