package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakurapuare.flightmanagement.mapper.user.PassengerMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Passenger;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.PassengerInfo;
import com.sakurapuare.flightmanagement.services.user.PassengerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerMapper passengerMapper) {
        this.passengerMapper = passengerMapper;
    }

    @Override
    public Passenger getPassengerByPassengerName(String username) {
        return passengerMapper.selectOne(
                new QueryWrapper<Passenger>()
                        .eq("passenger_name", username));
    }

    @Override
    public void register(long userId, UserRegisterDTO<PassengerInfo> passengerInfoUserRegisterDTO) {
        Passenger passenger = getPassengerByPassengerName(
                passengerInfoUserRegisterDTO.getData().getPassengerName());

        if (passenger != null) {
            return;
        }

        passenger = new Passenger();
        passenger.setUserId(userId);
        BeanUtils.copyProperties(passengerInfoUserRegisterDTO.getData(), passenger);
        passengerMapper.insert(passenger);

    }

    @Override
    public long count() {
        return passengerMapper.selectCount(null);
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerMapper.selectById(id);
    }
}
