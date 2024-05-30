package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.mapper.UserMapper;
import com.sakurapuare.flightmanagement.pojo.dto.auth.login.UserLoginDTO;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.User;
import com.sakurapuare.flightmanagement.pojo.vo.UserLoginVO;
import com.sakurapuare.flightmanagement.services.AuthService;
import com.sakurapuare.flightmanagement.utils.UserTypeUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/")
@Tag(name = "Auth Controller", description = "Auth API Endpoints")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Response<UserLoginVO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        User user = userMapper.findUserByUsernameAndPassword(
                userLoginDTO.getUsername(),
                userLoginDTO.getPassword());
        if (user == null) {
            return Response.error("Invalid username or password");
        } else {
            String token = authService.generateToken(user);
            UserLoginVO userLoginVO = new UserLoginVO(token);
            return Response.success("Login Success", userLoginVO);
        }
    }

    @PostMapping("/register")
    public Response<Void> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        // LOG
        log.info("Register: {}", userRegisterDTO);

        // Register Role
        User user = userMapper.findUserByUsername(userRegisterDTO.getUsername());
        int role = userRegisterDTO.getRole();
        if (user != null) {
            if (!user.getPassword().equals(userRegisterDTO.getPassword()))
                return Response.error("Invalid password");
            if (UserTypeUtils.isContain(user.getRole(), role))
                return Response.error("User already exists");
            user.setRole(UserTypeUtils.addRole(user.getRole(), role));
            userMapper.updateById(user);
            return Response.success("Update role success");
        } else {
            user = new User();
            BeanUtils.copyProperties(userRegisterDTO, user);
            userMapper.insert(user);
        }
        return Response.success("Register success");
    }

}
