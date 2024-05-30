package com.sakurapuare.flightmanagement.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "User Controller", description = "User API Endpoints")
public class UserController {


}
