package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LuggageDTO {

    @Positive(message = "The user ID must be positive")
    private Long userId;

    @Positive(message = "The order ID must be positive")
    private Long orderId;

    @Positive(message = "The weight must be positive")
    private Double weight;

}
