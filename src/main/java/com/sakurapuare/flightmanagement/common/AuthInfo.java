package com.sakurapuare.flightmanagement.common;

import lombok.Data;

@Data
public class AuthInfo {
    public long userId;

    public String username;

    public int role;
}
