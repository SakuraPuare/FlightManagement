package com.sakurapuare.flightmanagement.controllers;

import com.sakurapuare.flightmanagement.common.Response;
import com.sakurapuare.flightmanagement.constant.UserType;
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
import com.sakurapuare.flightmanagement.services.user.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

    private final UserService userService;

    private final AirlineService airlineService;

    private final MerchantService merchantService;

    private final PassengerService passengerService;

    private final StaffService staffService;

    public AuthController(AuthService authService, UserService userService, AirlineService airlineService,
                          MerchantService merchantService, PassengerService passengerService, StaffService staffService) {
        this.authService = authService;
        this.userService = userService;
        this.airlineService = airlineService;
        this.merchantService = merchantService;
        this.passengerService = passengerService;
        this.staffService = staffService;
    }

    @PostMapping("/login")
    public Response<UserLoginVO> login(
            @Validated @RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.getUserByUsernameAndPassword(
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
        User user = userService.getUserByUsername(airlineInfoUserRegisterDTO.getUsername());
        Airline airline = airlineService
                .findAirlineByAirlineCode(airlineInfoUserRegisterDTO.getData().getAirlineCode());

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
            user = userService.register(airlineInfoUserRegisterDTO);
        }

        if (airline == null) {
            airlineService.register(user.getUserId(), airlineInfoUserRegisterDTO);
            userService.updateUserType(user.getUserId(), UserType.AIRLINE);
            return Response.success("Airline Register success");
        }
        return Response.error("User already exists");
    }

    @PostMapping("/register/merchant")
    public Response<Void> register_merchant(
            @Valid @RequestBody UserRegisterDTO<MerchantInfo> merchantInfoUserRegisterDTO) {
        User user = userService.getUserByUsername(merchantInfoUserRegisterDTO.getUsername());
        Merchant merchant = merchantService
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
            user = userService.register(merchantInfoUserRegisterDTO);
        }

        if (merchant == null) {
            merchantService.register(user.getUserId(), merchantInfoUserRegisterDTO);
            userService.updateUserType(user.getUserId(), UserType.MERCHANT);
            return Response.success("Merchant Register success");
        }

        return Response.error("User already exists");
    }

    @PostMapping("/register/passenger")
    public Response<Void> register_passenger(
            @Valid @RequestBody UserRegisterDTO<PassengerInfo> passengerInfoUserRegisterDTO) {
        User user = userService.getUserByUsername(passengerInfoUserRegisterDTO.getUsername());
        Passenger passenger = passengerService.findPassengerByPassengerName(passengerInfoUserRegisterDTO.getUsername());

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
            user = userService.register(passengerInfoUserRegisterDTO);
        }

        if (passenger == null) {
            passengerService.register(user.getUserId(), passengerInfoUserRegisterDTO);
            userService.updateUserType(user.getUserId(), UserType.PASSENGER);
            return Response.success("Passenger Register success");
        }

        return Response.error("User already exists");
    }

    @PostMapping("/register/staff")
    public Response<Void> register_staff(@Valid @RequestBody UserRegisterDTO<StaffInfo> baseUserRegisterDTO) {
        User user = userService.getUserByUsername(baseUserRegisterDTO.getUsername());
        Staff staff = staffService.findStaffByStaffName(baseUserRegisterDTO.getUsername());

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
            user = userService.register(baseUserRegisterDTO);
        }

        if (staff == null) {
            staffService.register(user.getUserId(), baseUserRegisterDTO);
            userService.updateUserType(user.getUserId(), UserType.STAFF);
            return Response.success("Staff Register success");
        }

        return Response.error("User already exists");
    }

    @PostMapping("/register")
    public Response<Void> register_user(@Valid @RequestBody BaseUserRegisterDTO baseUserRegisterDTO) {
        User user = userService.getUserByUsername(baseUserRegisterDTO.getUsername());
        if (user == null) {
            userService.register(baseUserRegisterDTO);
            return Response.success("Register success");
        }
        return Response.error("User already exists");
    }

}
