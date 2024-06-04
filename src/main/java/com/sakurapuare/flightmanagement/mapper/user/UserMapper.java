package com.sakurapuare.flightmanagement.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
