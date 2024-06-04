package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;

public interface AirlineService {

    Airline findAirlineByAirlineCode(String airlineCode);

    Airline register(Long userId, UserRegisterDTO<AirlineInfo> airlineInfoUserRegisterDTO);

    Airline findAirlineById(Long airlineId);

}
