package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.OrderMapper;
import com.sakurapuare.flightmanagement.mapper.user.PassengerMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Passenger;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.PassengerInfo;
import com.sakurapuare.flightmanagement.services.user.PassengerService;
import com.sakurapuare.flightmanagement.services.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerMapper passengerMapper;

    private final UserService userService;

    private final OrderMapper orderMapper;

    public PassengerServiceImpl(PassengerMapper passengerMapper, OrderMapper orderMapper, UserService userService) {
        this.passengerMapper = passengerMapper;
        this.orderMapper = orderMapper;
        this.userService = userService;
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

    @Override
    public List<Passenger> getPassengersByPagination(int page, int count) {
        Page<Passenger> passengerPage = new Page<>(page, count);
        return passengerMapper.selectPage(passengerPage, null).getRecords();
    }
}
