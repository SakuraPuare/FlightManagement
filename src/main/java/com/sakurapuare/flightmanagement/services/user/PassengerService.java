package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Passenger;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.PassengerInfo;

public interface PassengerService {

    Passenger getPassengerByPassengerName(String username);

    void register(long userId, UserRegisterDTO<PassengerInfo> passengerInfoUserRegisterDTO);

}
