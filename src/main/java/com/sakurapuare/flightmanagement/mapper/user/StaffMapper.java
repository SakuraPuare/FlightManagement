package com.sakurapuare.flightmanagement.mapper.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

    default Staff findStaffByStaffName(String username) {
        return this.selectOne(
                new QueryWrapper<Staff>().eq("staff_name", username));
    }

}
