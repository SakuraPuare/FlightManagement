package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.user.AirlineMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;
import com.sakurapuare.flightmanagement.services.user.AirlineService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineMapper airlineMapper;

    public AirlineServiceImpl(AirlineMapper airlineMapper) {
        this.airlineMapper = airlineMapper;
    }

    @Override
    public Airline getAirlineByAirlineCode(String airlineCode) {
        return airlineMapper.selectOne(
                new QueryWrapper<Airline>()
                        .eq("airline_code", airlineCode));
    }

    @Override
    public void register(long userId, UserRegisterDTO<AirlineInfo> airlineInfoUserRegisterDTO) {
        Airline airline = getAirlineByAirlineCode(
                airlineInfoUserRegisterDTO.getData().getAirlineCode());
        if (airline != null) {
            return;
        }

        airline = new Airline();
        airline.setUserId(userId);
        BeanUtils.copyProperties(airlineInfoUserRegisterDTO.getData(), airline);
        airlineMapper.insert(airline);

    }

    @Override
    public Airline getAirlineById(long airlineId) {
        return airlineMapper.selectById(airlineId);
    }

    @Override
    public long count() {
        return airlineMapper.selectCount(null);
    }

    @Override
    public List<Airline> getAirlinesByPagination(int page, int count) {
        Page<Airline> airlinePage = new Page<>(page, count);
        return airlineMapper.selectPage(airlinePage, null).getRecords();
    }

}
