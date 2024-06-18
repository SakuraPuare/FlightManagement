package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Passenger;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.PassengerInfo;

import java.util.List;

public interface PassengerService {

    Passenger getPassengerByPassengerName(String username);

    void register(long userId, UserRegisterDTO<PassengerInfo> passengerInfoUserRegisterDTO);

    long count();

    Passenger getPassengerById(Long id);

    List<Passenger> getPassengersByPagination(int page, int count);
}
