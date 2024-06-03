package com.sakurapuare.flightmanagement.pojo.dto.auth.register;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegisterDTO<T> extends BaseUserRegisterDTO {
    @NotNull(message = "Register info is required")
    T data;
}
