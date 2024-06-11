package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.StaffInfo;

public interface StaffService {

    Staff findStaffByStaffName(String username);

    Staff register(Long userId, UserRegisterDTO<StaffInfo> baseUserRegisterDTO);

}