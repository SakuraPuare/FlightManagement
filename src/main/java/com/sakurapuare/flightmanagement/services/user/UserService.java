package com.sakurapuare.flightmanagement.services.user;

import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;

public interface UserService {

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);

    User register(BaseUserRegisterDTO baseUserRegisterDTO);

    boolean updateUserType(Long userId, int role);

}
