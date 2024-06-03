package com.sakurapuare.flightmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.Flight;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlightMapper extends BaseMapper<Flight> {

}