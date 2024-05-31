package com.sakurapuare.flightmanagement.pojo.dto.auth.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BaseUserRegisterDTO {
    @NotBlank(message = "Username is required")
    public String username;

    @NotBlank(message = "Password is required")
    public String password;

    @Email(message = "Email is invalid")
    public String email;
}