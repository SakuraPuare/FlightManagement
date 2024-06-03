package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.constant.UserType;
import com.sakurapuare.flightmanagement.mapper.user.*;
import com.sakurapuare.flightmanagement.pojo.dto.auth.login.UserLoginDTO;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.BaseUserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.dto.auth.register.UserRegisterDTO;
import com.sakurapuare.flightmanagement.pojo.entity.user.*;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.AirlineInfo;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.MerchantInfo;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.PassengerInfo;
import com.sakurapuare.flightmanagement.pojo.entity.user.info.StaffInfo;
import com.sakurapuare.flightmanagement.pojo.vo.UserLoginVO;
import com.sakurapuare.flightmanagement.services.AuthService;
import com.sakurapuare.flightmanagement.utils.UserTypeUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    private final AuthService authService;

    private final UserMapper userMapper;

    private final AirlineMapper airlineMapper;

    private final MerchantMapper merchantMapper;

    private final PassengerMapper passengerMapper;

    private final StaffMapper staffMapper;

    public AuthController(AuthService authService, UserMapper userMapper, AirlineMapper airlineMapper,
                          MerchantMapper merchantMapper, PassengerMapper passengerMapper, StaffMapper staffMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.airlineMapper = airlineMapper;
        this.merchantMapper = merchantMapper;
        this.passengerMapper = passengerMapper;
        this.staffMapper = staffMapper;
    }

    @PostMapping("/login")
    public Response<UserLoginVO> login(
            @Validated @RequestBody UserLoginDTO userLoginDTO) {
        User user = userMapper.findUserByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
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

    @PostMapping("/register/merchant")
    public Response<Void> register_merchant(
            @Valid @RequestBody UserRegisterDTO<MerchantInfo> merchantInfoUserRegisterDTO) {
        User user = userMapper.findUserByUsername(merchantInfoUserRegisterDTO.getUsername());
        Merchant merchant = merchantMapper
                .findMerchantByMerchantName(merchantInfoUserRegisterDTO.getData().getMerchantName());

        if (user != null && merchant != null) {
            return Response.error("Merchant already exists");
        }

        // check password
        if (user != null) {
            if (!merchantInfoUserRegisterDTO.getPassword().equals(user.getPassword())) {
                return Response.error("Password not match");
            }
        }

        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(merchantInfoUserRegisterDTO, user);
            user.setRole(UserType.MERCHANT);
            userMapper.insert(user);
        }

        if (merchant == null) {
            merchant = new Merchant();
            BeanUtils.copyProperties(merchantInfoUserRegisterDTO.getData(), merchant);
            user.setRole(UserTypeUtils.addRole(user.getRole(), UserType.MERCHANT));
            merchant.setUserId(user.getUserId());
            merchantMapper.insert(merchant);
            userMapper.updateById(user);
            return Response.success("Merchant Register success");
        }

        return Response.error("User already exists");
    }

    @PostMapping("/register/passenger")
    public Response<Void> register_passenger(
            @Valid @RequestBody UserRegisterDTO<PassengerInfo> passengerInfoUserRegisterDTO) {
        User user = userMapper.findUserByUsername(passengerInfoUserRegisterDTO.getUsername());
        Passenger passenger = passengerMapper.findPassengerByPassengerName(passengerInfoUserRegisterDTO.getUsername());

        if (user != null && passenger != null) {
            return Response.error("Passenger already exists");
        }

        // check password
        if (user != null) {
            if (!passengerInfoUserRegisterDTO.getPassword().equals(user.getPassword())) {
                return Response.error("Password not match");
            }
        }

        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(passengerInfoUserRegisterDTO, user);
            user.setRole(UserType.PASSENGER);
            userMapper.insert(user);
        }

        if (passenger == null) {
            passenger = new Passenger();
            BeanUtils.copyProperties(passengerInfoUserRegisterDTO.getData(), passenger);
            user.setRole(UserTypeUtils.addRole(user.getRole(), UserType.PASSENGER));
            passenger.setUserId(user.getUserId());
            passengerMapper.insert(passenger);
            userMapper.updateById(user);
            return Response.success("Passenger Register success");
        }

        return Response.error("User already exists");
    }

    @PostMapping("/register/staff")
    public Response<Void> register_staff(@Valid @RequestBody UserRegisterDTO<StaffInfo> baseUserRegisterDTO) {
        User user = userMapper.findUserByUsername(baseUserRegisterDTO.getUsername());
        Staff staff = staffMapper.findStaffByStaffName(baseUserRegisterDTO.getUsername());

        if (user != null && staff != null) {
            return Response.error("Staff already exists");
        }

        // check password
        if (user != null) {
            if (!baseUserRegisterDTO.getPassword().equals(user.getPassword())) {
                return Response.error("Password not match");
            }
        }

        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(baseUserRegisterDTO, user);
            user.setRole(UserType.STAFF);
            userMapper.insert(user);
        }

        if (staff == null) {
            staff = new Staff();
            BeanUtils.copyProperties(baseUserRegisterDTO.getData(), staff);
            user.setRole(UserTypeUtils.addRole(user.getRole(), UserType.STAFF));
            staff.setUserId(user.getUserId());
            staffMapper.insert(staff);
            userMapper.updateById(user);
            return Response.success("Staff Register success");
        }

        return Response.error("User already exists");
    }

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

}
