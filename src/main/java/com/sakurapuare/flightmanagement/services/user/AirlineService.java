package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;

import java.util.List;

public interface AirlineService {

    Airline getAirlineByAirlineCode(String airlineCode);

    void register(long userId, UserRegisterDTO<AirlineInfo> airlineInfoUserRegisterDTO);

    Airline getAirlineById(long airlineId);

    long count();

    List<Airline> getAirlinesByPagination(int page, int count);

}
