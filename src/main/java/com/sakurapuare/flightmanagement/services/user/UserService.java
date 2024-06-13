package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;

public interface UserService {

    User getUserById(long userId);

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByUsername(String username);

    User register(BaseUserRegisterDTO baseUserRegisterDTO);

    void updateUserType(long userId, int role);

    long count();

}
