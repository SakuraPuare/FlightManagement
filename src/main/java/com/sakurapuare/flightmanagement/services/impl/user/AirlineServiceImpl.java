package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakurapuare.flightmanagement.mapper.user.AirlineMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineMapper airlineMapper;

    public AirlineServiceImpl(AirlineMapper airlineMapper) {
        this.airlineMapper = airlineMapper;
    }

    @Override
    public Airline findAirlineByAirlineCode(String airlineCode) {
        return airlineMapper.selectOne(
                new QueryWrapper<Airline>()
                        .eq("airline_code", airlineCode));
    }

    @Override
    public Airline register(Long userId, UserRegisterDTO<AirlineInfo> airlineInfoUserRegisterDTO) {
        Airline airline = findAirlineByAirlineCode(
                airlineInfoUserRegisterDTO.getData().getAirlineCode());
        if (airline != null) {
            return airline;
        }

        airline = new Airline();
        airline.setUserId(userId);
        BeanUtils.copyProperties(airlineInfoUserRegisterDTO.getData(), airline);
        airlineMapper.insert(airline);

        return airline;
    }

    @Override
    public Airline findAirlineById(Long airlineId) {
        return airlineMapper.selectById(airlineId);
    }
}
