package com.sakurapuare.flightmanagement.services.user;

import java.util.List;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;

public interface UserService {

    User getUserById(long userId);

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByUsername(String username);

    User register(BaseUserRegisterDTO baseUserRegisterDTO);

    void updateRole(long userId, int role);

    long count();

    List<User> getUserByPagination(int page, int count);

}
