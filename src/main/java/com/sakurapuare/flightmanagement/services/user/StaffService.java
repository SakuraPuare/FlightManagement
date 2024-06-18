package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.StaffInfo;

import java.util.List;

public interface StaffService {

    Staff getStaffByStaffName(String username);

    void register(long userId, UserRegisterDTO<StaffInfo> baseUserRegisterDTO);

    long count();

    Staff getStaffById(Long id);

    List<Staff> getStaffByPagination(int page, int count);

}
