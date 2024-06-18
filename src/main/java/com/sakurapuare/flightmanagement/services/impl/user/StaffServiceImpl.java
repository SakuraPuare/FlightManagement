package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.user.StaffMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.StaffInfo;
import com.sakurapuare.flightmanagement.services.user.StaffService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffMapper staffMapper;

    public StaffServiceImpl(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Override
    public Staff getStaffByStaffName(String username) {
        return staffMapper.selectOne(
                new QueryWrapper<Staff>()
                        .eq("staff_name", username));
    }

    @Override
    public void register(long userId, UserRegisterDTO<StaffInfo> baseUserRegisterDTO) {
        Staff staff = getStaffByStaffName(
                baseUserRegisterDTO.getData().getStaffName());

        if (staff != null) {
            return;
        }

        staff = new Staff();
        staff.setUserId(userId);
        BeanUtils.copyProperties(baseUserRegisterDTO.getData(), staff);
        staffMapper.insert(staff);

    }

    @Override
    public long count() {
        return staffMapper.selectCount(null);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffMapper.selectById(id);
    }

    @Override
    public List<Staff> getStaffByPagination(int page, int count) {
        Page<Staff> staffPage = new Page<>(page, count);
        return staffMapper.selectPage(staffPage, null).getRecords();
    }
}
