package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDTO {

    @Positive(message = "Flight ID must be positive")
    private Long flightId;

    @NotEmpty(message = "Seat class must not be empty")
    private String seatClass;

    @Positive(message = "Quota must be positive")
    private Long quota;

    @NotEmpty(message = "Price must not be empty")
    private BigDecimal price;
}
