package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.dto.auth.login.UserLoginDTO;
import com.sakurapuare.flightmanagement.pojo.entity.User;

public interface AuthService {

    User login(UserLoginDTO userLoginDTO);

    String generateToken(User user);
}
