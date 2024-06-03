package com.sakurapuare.flightmanagement.utils;

import com.sakurapuare.flightmanagement.constant.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserTypeUtils {

    // 掩码 实现用户类型的判断
    // Passenger, Merchant, Airline, Staff

    public static boolean isContain(int userType, int type) {
        return (userType & type) == type;
    }

    public static boolean isPassenger(int userType) {
        return (userType & UserType.PASSENGER) == UserType.PASSENGER;
    }

    public static boolean isMerchant(int userType) {
        return (userType & UserType.MERCHANT) == UserType.MERCHANT;
    }

    public static boolean isAirline(int userType) {
        return (userType & UserType.AIRLINE) == UserType.AIRLINE;
    }

    public static boolean isStaff(int userType) {
        return (userType & UserType.STAFF) == UserType.STAFF;
    }

    public static boolean isAll(int userType) {
        return userType == UserType.ALL;
    }

    public static int addRole(int userType, int type) {
        return userType | type;
    }

    public static int addPassenger(int userType) {
        return userType | UserType.PASSENGER;
    }

    public static int addMerchant(int userType) {
        return userType | UserType.MERCHANT;
    }

    public static int addAirline(int userType) {
        return userType | UserType.AIRLINE;
    }

    public static int addStaff(int userType) {
        return userType | UserType.STAFF;
    }

    public static int removePassenger(int userType) {
        return userType & UserType.ALL - UserType.PASSENGER;
    }

    public static int removeMerchant(int userType) {
        return userType & UserType.ALL - UserType.MERCHANT;
    }

    public static int removeAirline(int userType) {
        return userType & UserType.ALL - UserType.AIRLINE;
    }

    public static int removeStaff(int userType) {
        return userType & UserType.ALL - UserType.STAFF;
    }

    public static List<String> getRoleList(int userType) {
        List<String> userTypeList = new ArrayList<>();
        if (isPassenger(userType)) {
            userTypeList.add("Passenger");
        }
        if (isMerchant(userType)) {
            userTypeList.add("Merchant");
        }
        if (isAirline(userType)) {
            userTypeList.add("Airline");
        }
        if (isStaff(userType)) {
            userTypeList.add("Staff");
        }
        return userTypeList;
    }

    public static int getRoleCount(int userType) {
        int count = 0;
        if (isPassenger(userType)) {
            count++;
        }
        if (isMerchant(userType)) {
            count++;
        }
        if (isAirline(userType)) {
            count++;
        }
        if (isStaff(userType)) {
            count++;
        }
        return count;
    }
}
