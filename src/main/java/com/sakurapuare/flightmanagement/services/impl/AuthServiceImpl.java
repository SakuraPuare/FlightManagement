package com.sakurapuare.flightmanagement.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakurapuare.flightmanagement.mapper.UserMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.login.UserLoginDTO;
import com.sakurapuare.flightmanagement.pojo.entity.User;
import com.sakurapuare.flightmanagement.services.AuthService;
import com.sakurapuare.flightmanagement.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLoginDTO.getUsername());
        queryWrapper.eq("password", userLoginDTO.getPassword());
        return userMapper.selectOne(queryWrapper);
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
