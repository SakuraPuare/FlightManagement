package com.sakurapuare.flightmanagement.pojo.dto.auth.register;

import com.sakurapuare.flightmanagement.constant.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "Username is required")
    public String username;
    @NotBlank(message = "Password is required")
    public String password;
    @Email(message = "Email is invalid")
    public String email;
    @Size(min = 0, max = UserType.ALL, message = "Role is invalid")
    public int role;
}
