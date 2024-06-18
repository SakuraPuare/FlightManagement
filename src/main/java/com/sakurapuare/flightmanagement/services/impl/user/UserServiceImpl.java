package com.sakurapuare.flightmanagement.services.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakurapuare.flightmanagement.mapper.user.UserMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.services.user.UserService;
import com.sakurapuare.flightmanagement.utils.RoleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username)
                        .eq("password", password));
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username));
    }

    @Override
    public User register(BaseUserRegisterDTO baseUserRegisterDTO) {
        User user = getUserByUsername(
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
    public void updateRole(long userId, int role) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return;
        }

        user.setRole(RoleUtils.addRole(user.getRole(), role));
        userMapper.updateById(user);

    }

    @Override
    public long count() {
        return userMapper.selectCount(null);
    }

    @Override
    public List<User> getUserByPagination(int page, int count) {
        Page<User> userPage = new Page<>(page, count);
        return userMapper.selectPage(userPage, null).getRecords();
    }
}
