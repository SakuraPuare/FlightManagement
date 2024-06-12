package com.sakurapuare.flightmanagement.services;

import com.sakurapuare.flightmanagement.pojo.entity.user.User;

public interface AuthService {

    String generateToken(User user);
}
