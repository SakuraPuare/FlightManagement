package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sakurapuare.flightmanagement.mapper.user.UserMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.services.user.UserService;
import com.sakurapuare.flightmanagement.utils.UserTypeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username)
                        .eq("password", password));
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username));
    }

    @Override
    public User register(BaseUserRegisterDTO baseUserRegisterDTO) {
        User user = findUserByUsername(
                baseUserRegisterDTO.getUsername());
        if (user != null) {
            return user;
        }

        user = new User();
        BeanUtils.copyProperties(baseUserRegisterDTO, user);
        userMapper.insert(user);

        return user;
    }

    @Override
    public boolean updateUserType(Long userId, int role) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return false;
        }

        user.setRole(UserTypeUtils.addRole(user.getRole(), role));
        userMapper.updateById(user);

        return true;
    }
}
