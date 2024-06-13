package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RequestDTO {
    @NotEmpty(message = "info cannot be empty")
    private String info;

    private String status = "unhandled";
}
