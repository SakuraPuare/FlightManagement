package com.sakurapuare.flightmanagement.pojo.dto.auth.register;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StaffRegisterDTO extends UserRegisterDTO {

    StaffInfo data;
}

@Data
class StaffInfo {

}
