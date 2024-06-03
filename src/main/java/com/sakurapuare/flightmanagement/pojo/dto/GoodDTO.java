package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoodDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String description;

    private String category;

    @NotBlank(message = "Price cannot be empty")
    private double price;

    @NotBlank(message = "Stock cannot be empty")
    private int stock;
}
