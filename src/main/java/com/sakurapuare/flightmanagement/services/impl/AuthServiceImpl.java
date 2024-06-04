package com.sakurapuare.flightmanagement.services.impl;

import com.sakurapuare.flightmanagement.pojo.dto.auth.login.UserLoginDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.services.AuthService;
import com.sakurapuare.flightmanagement.services.user.UserService;
import com.sakurapuare.flightmanagement.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        User user = userService.findUserByUsernameAndPassword(
                userLoginDTO.getUsername(), userLoginDTO.getPassword());
        return user != null;
    }

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userid", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());

        return JwtTokenUtil.createToken(claims);
    }

}
