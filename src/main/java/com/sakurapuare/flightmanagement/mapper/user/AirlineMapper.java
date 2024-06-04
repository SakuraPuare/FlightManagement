package com.sakurapuare.flightmanagement.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AirlineMapper extends BaseMapper<Airline> {

}