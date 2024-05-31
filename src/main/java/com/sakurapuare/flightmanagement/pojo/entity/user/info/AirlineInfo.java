package com.sakurapuare.flightmanagement.pojo.entity.user.info;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AirlineInfo {
    @NotBlank(message = "Airline name is required")
    String airlineName;

    @NotBlank(message = "Airline code is required")
    String airlineCode;

    @NotBlank(message = "Airline country is required")
    String airlineCountry;

    @NotBlank(message = "Airline description is required")
    String airlineDescription;

}