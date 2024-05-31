package com.sakurapuare.flightmanagement.pojo.entity.user.info;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MerchantInfo {
    @NotBlank(message = "Merchant name is required")
    String merchantName;

    @NotBlank(message = "Merchant phone is required")
    String merchantAddress;

    @NotBlank(message = "Merchant description is required")
    String merchantDescription;
}