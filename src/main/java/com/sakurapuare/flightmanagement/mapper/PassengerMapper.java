package com.sakurapuare.flightmanagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.Passenger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerMapper extends BaseMapper<Passenger> {

    default Passenger findPassengerByPassengerName(String username) {
        return this.selectOne(
                new QueryWrapper<Passenger>().eq("passenger_name", username));
    }

}
