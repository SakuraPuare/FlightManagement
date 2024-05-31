package com.sakurapuare.flightmanagement.pojo.entity.user.info;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StaffInfo {
    @NotBlank(message = "Staff name is required")
    String staffName;
}
