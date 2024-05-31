package com.sakurapuare.flightmanagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AirlineMapper extends BaseMapper<Airline> {

    default Airline findAirlineByAirlineCode(String airlineCode) {
        return this.selectOne(
                new QueryWrapper<Airline>()
                        .eq("airline_code", airlineCode));
    }
}