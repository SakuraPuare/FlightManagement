package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.constant.UserType;
import com.sakurapuare.flightmanagement.mapper.*;
import com.sakurapuare.flightmanagement.pojo.dto.auth.login.UserLoginDTO;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.Airline;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;
import com.sakurapuare.flightmanagement.pojo.vo.UserLoginVO;
import com.sakurapuare.flightmanagement.services.AuthService;
import com.sakurapuare.flightmanagement.utils.UserTypeUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private AirlineMapper airlineMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private StaffMapper staffMapper;

    @PostMapping("/login")
    public Response<UserLoginVO> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
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

    @PostMapping("/register/airline")
    public Response<Void> register_airline(
            @Valid @RequestBody UserRegisterDTO<AirlineInfo> airlineInfoUserRegisterDTO) {
        User user = userMapper.findUserByUsername(airlineInfoUserRegisterDTO.getUsername());
        Airline airline = airlineMapper.findAirlineByAirlineCode(airlineInfoUserRegisterDTO.getData().getAirlineCode());

        if (user != null && airline != null) {
            return Response.error("Airline already exists");
        }

        // check password
        if (user != null) {
            if (!airlineInfoUserRegisterDTO.getPassword().equals(user.getPassword())) {
                return Response.error("Password not match");
            }
        }

        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(airlineInfoUserRegisterDTO, user);
            user.setRole(UserType.AIRLINE);
            userMapper.insert(user);
        }
        if (airline == null) {
            airline = new Airline();
            BeanUtils.copyProperties(airlineInfoUserRegisterDTO.getData(), airline);
            user.setRole(UserTypeUtils.addRole(user.getRole(), UserType.AIRLINE));
            airline.setUserId(user.getUserId());
            airlineMapper.insert(airline);
            userMapper.updateById(user);
            return Response.success("Airline Register success");
        }
        return Response.error("User already exists");
    }

    // @PostMapping("/register/merchant")
    // public Response<Void> register_merchant(@Valid @RequestBody
    // MerchantRegisterDTO merchantRegisterDTO) {
    // User user = userMapper.findUserByUsername(merchantRegisterDTO.getUsername());
    // Merchant merchant =
    // merchantMapper.findMerchantByMerchantCode(merchantRegisterDTO.getData().getMerchantCode());

    // if (user == null) {
    // user = new User();
    // BeanUtils.copyProperties(merchantRegisterDTO, user);
    // user.setRole(UserType.MERCHANT);
    // userMapper.insert(user);
    // return Response.success("Merchant Register success");
    // }
    // return Response.error("User already exists");
    // }

    @PostMapping("/register")
    public Response<Void> register_user(@Valid @RequestBody BaseUserRegisterDTO baseUserRegisterDTO) {
        User user = userMapper.findUserByUsername(baseUserRegisterDTO.getUsername());
        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(baseUserRegisterDTO, user);
            userMapper.insert(user);
            return Response.success("Register success");
        }
        return Response.error("User already exists");
    }

    // public Response<Void> register_user(@Valid @RequestBody UserRegisterDTO
    // userRegisterDTO) {
    // User user = userMapper.findUserByUsername(userRegisterDTO.getUsername());
    // int role = userRegisterDTO.getRole();

    // // ALL type is not allowed to register
    // if (role == UserType.ALL || UserTypeUtils.getRoleCount(role) != 1)
    // return Response.error("Invalid role");
    // if (userRegisterDTO.getData() == null)
    // return Response.error("User info is required");

    // if (user == null) {
    // user = new User();
    // BeanUtils.copyProperties(userRegisterDTO, user);
    // userMapper.insert(user);
    // if (role != 0) {
    // if (userRegisterDTO.getData() == null)
    // return Response.error("User info is required");

    // UserInfo userInfo = UserRegisterInfoFactory.create(userRegisterDTO, role);
    // if (userInfo == null || !userInfo.checkDataIntegrity())
    // return Response.error("Data integrity error");
    // }
    // } else {
    // if (!user.getPassword().equals(userRegisterDTO.getPassword()))
    // return Response.error("Invalid password");
    // if (UserTypeUtils.isContain(user.getRole(), role))
    // return Response.error("User already exists");
    // if (userRegisterDTO.getData() == null)
    // return Response.error("User info is required");

    // UserInfo userInfo = UserRegisterInfoFactory.create(userRegisterDTO, role);
    // if (userInfo == null || !userInfo.checkDataIntegrity())
    // return Response.error("Data integrity error");
    // }

    // return Response.success("Register success");
    // }

}
