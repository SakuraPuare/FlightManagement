package com.sakurapuare.flightmanagement.pojo.dto.auth.register;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterDTO<T> extends BaseUserRegisterDTO {
    @NotNull(message = "Merchant info is required")
    T data;
}
