package com.sakurapuare.flightmanagement.utils;

import com.sakurapuare.flightmanagement.constant.UserType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTypeUtilsTest {

    @Test
    void testIsContain() {
        int userType = UserType.PASSENGER | UserType.MERCHANT;
        assertTrue(UserTypeUtils.isContain(userType, UserType.PASSENGER));
        assertTrue(UserTypeUtils.isContain(userType, UserType.MERCHANT));
        assertFalse(UserTypeUtils.isContain(userType, UserType.AIRLINE));
        assertFalse(UserTypeUtils.isContain(userType, UserType.STAFF));
    }

    @Test
    void testIsPassenger() {
        int userType = UserType.PASSENGER;
        assertTrue(UserTypeUtils.isPassenger(userType));
        assertFalse(UserTypeUtils.isPassenger(UserType.MERCHANT));
        assertFalse(UserTypeUtils.isPassenger(UserType.AIRLINE));
        assertFalse(UserTypeUtils.isPassenger(UserType.STAFF));
    }

    @Test
    void testIsMerchant() {
        int userType = UserType.MERCHANT;
        assertTrue(UserTypeUtils.isMerchant(userType));
        assertFalse(UserTypeUtils.isMerchant(UserType.PASSENGER));
        assertFalse(UserTypeUtils.isMerchant(UserType.AIRLINE));
        assertFalse(UserTypeUtils.isMerchant(UserType.STAFF));
    }

    @Test
    void testIsAirline() {
        int userType = UserType.AIRLINE;
        assertTrue(UserTypeUtils.isAirline(userType));
        assertFalse(UserTypeUtils.isAirline(UserType.PASSENGER));
        assertFalse(UserTypeUtils.isAirline(UserType.MERCHANT));
        assertFalse(UserTypeUtils.isAirline(UserType.STAFF));
    }

    @Test
    void testIsStaff() {
        int userType = UserType.STAFF;
        assertTrue(UserTypeUtils.isStaff(userType));
        assertFalse(UserTypeUtils.isStaff(UserType.PASSENGER));
        assertFalse(UserTypeUtils.isStaff(UserType.MERCHANT));
        assertFalse(UserTypeUtils.isStaff(UserType.AIRLINE));
    }

    @Test
    void testAddRole() {
        int userType = UserType.PASSENGER;
        userType = UserTypeUtils.addRole(userType, UserType.MERCHANT);
        assertTrue(UserTypeUtils.isMerchant(userType));
        assertTrue(UserTypeUtils.isPassenger(userType));
        assertFalse(UserTypeUtils.isAirline(userType));
        assertFalse(UserTypeUtils.isStaff(userType));
    }
}