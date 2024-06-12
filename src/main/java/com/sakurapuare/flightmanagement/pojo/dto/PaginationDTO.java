package com.sakurapuare.flightmanagement.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PaginationDTO {
    @PositiveOrZero(message = "Page number must be greater than or equal to 0")
    Integer page;

    @Positive(message = "Page count must be greater than 0")
    @Max(value = 100, message = "Page count must be less than or equal to 100")
    Integer count;
}
