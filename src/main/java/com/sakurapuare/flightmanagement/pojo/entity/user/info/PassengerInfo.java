package com.sakurapuare.flightmanagement.pojo.entity.user.info;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PassengerInfo {
    @NotBlank(message = "Passenger name is required")
    String passengerName;

    @Pattern(regexp = "^(\\d{11})$", message = "Passenger phone is invalid")
    String passengerPhone;

    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "Passenger identity number is invalid")
    String identityNumber;
}