package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakurapuare.flightmanagement.mapper.user.StaffMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.StaffInfo;
import com.sakurapuare.flightmanagement.services.user.StaffService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;

    public StaffServiceImpl(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Override
    public Staff findStaffByStaffName(String username) {
        return staffMapper.selectOne(
                new QueryWrapper<Staff>()
                        .eq("staff_name", username));
    }

    @Override
    public Staff register(Long userId, UserRegisterDTO<StaffInfo> baseUserRegisterDTO) {
        Staff staff = findStaffByStaffName(
                baseUserRegisterDTO.getData().getStaffName());

        if (staff != null) {
            return staff;
        }

        staff = new Staff();
        staff.setUserId(userId);
        BeanUtils.copyProperties(baseUserRegisterDTO.getData(), staff);
        staffMapper.insert(staff);

        return staff;
    }
}
