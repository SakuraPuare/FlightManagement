package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String description;

    private String category;

    @NotBlank(message = "Price cannot be empty")
    private BigDecimal price;

    @NotBlank(message = "Stock cannot be empty")
    private Integer stock;
}
