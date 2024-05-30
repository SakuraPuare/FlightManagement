package com.sakurapuare.flightmanagement.pojo.dto.auth.register;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PassengerRegisterDTO extends UserRegisterDTO {
    PassengerInfo data;
}

@Data
class PassengerInfo {
    String identityNumber;
}