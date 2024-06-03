package com.sakurapuare.flightmanagement.utils;

import com.sakurapuare.flightmanagement.constant.UserType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTypeUtilsTest {

    @Test
    public void testIsContain() {
        int userType = UserType.PASSENGER | UserType.MERCHANT;
        assertTrue(UserTypeUtils.isContain(userType, UserType.PASSENGER));
        assertTrue(UserTypeUtils.isContain(userType, UserType.MERCHANT));
        assertFalse(UserTypeUtils.isContain(userType, UserType.AIRLINE));
        assertFalse(UserTypeUtils.isContain(userType, UserType.STAFF));
    }

    @Test
    public void testIsPassenger() {
        int userType = UserType.PASSENGER;
        assertTrue(UserTypeUtils.isPassenger(userType));
        assertFalse(UserTypeUtils.isPassenger(UserType.MERCHANT));
    }

    @Test
    public void testIsMerchant() {
        int userType = UserType.MERCHANT;
        assertTrue(UserTypeUtils.isMerchant(userType));
        assertFalse(UserTypeUtils.isMerchant(UserType.PASSENGER));
    }

    @Test
    public void testIsAirline() {
        int userType = UserType.AIRLINE;
        assertTrue(UserTypeUtils.isAirline(userType));
        assertFalse(UserTypeUtils.isAirline(UserType.PASSENGER));
    }

    @Test
    public void testIsStaff() {
        int userType = UserType.STAFF;
        assertTrue(UserTypeUtils.isStaff(userType));
        assertFalse(UserTypeUtils.isStaff(UserType.PASSENGER));
    }

    @Test
    public void testIsAll() {
        int userType = UserType.ALL;
        assertTrue(UserTypeUtils.isAll(userType));
        assertFalse(UserTypeUtils.isAll(UserType.PASSENGER));
    }

    @Test
    public void testAddRole() {
        int userType = UserType.PASSENGER;
        userType = UserTypeUtils.addRole(userType, UserType.MERCHANT);
        assertTrue(UserTypeUtils.isMerchant(userType));
    }

    @Test
    public void testAddPassenger() {
        int userType = UserType.MERCHANT;
        userType = UserTypeUtils.addPassenger(userType);
        assertTrue(UserTypeUtils.isPassenger(userType));
    }

    @Test
    public void testAddMerchant() {
        int userType = UserType.PASSENGER;
        userType = UserTypeUtils.addMerchant(userType);
        assertTrue(UserTypeUtils.isMerchant(userType));
    }

    @Test
    public void testAddAirline() {
        int userType = UserType.PASSENGER;
        userType = UserTypeUtils.addAirline(userType);
        assertTrue(UserTypeUtils.isAirline(userType));
    }

    @Test
    public void testAddStaff() {
        int userType = UserType.PASSENGER;
        userType = UserTypeUtils.addStaff(userType);
        assertTrue(UserTypeUtils.isStaff(userType));
    }

    @Test
    public void testRemovePassenger() {
        int userType = UserType.PASSENGER | UserType.MERCHANT;
        userType = UserTypeUtils.removePassenger(userType);
        assertFalse(UserTypeUtils.isPassenger(userType));
        assertTrue(UserTypeUtils.isMerchant(userType));
    }

    @Test
    public void testRemoveMerchant() {
        int userType = UserType.PASSENGER | UserType.MERCHANT;
        userType = UserTypeUtils.removeMerchant(userType);
        assertFalse(UserTypeUtils.isMerchant(userType));
        assertTrue(UserTypeUtils.isPassenger(userType));
    }

    @Test
    public void testRemoveAirline() {
        int userType = UserType.PASSENGER | UserType.AIRLINE;
        userType = UserTypeUtils.removeAirline(userType);
        assertFalse(UserTypeUtils.isAirline(userType));
        assertTrue(UserTypeUtils.isPassenger(userType));
    }

    @Test
    public void testRemoveStaff() {
        int userType = UserType.PASSENGER | UserType.STAFF;
        userType = UserTypeUtils.removeStaff(userType);
        assertFalse(UserTypeUtils.isStaff(userType));
        assertTrue(UserTypeUtils.isPassenger(userType));
    }

    @Test
    public void testGetRoleList() {
        int userType = UserType.PASSENGER | UserType.MERCHANT | UserType.AIRLINE;
        List<String> expectedRoles = Arrays.asList("Passenger", "Merchant", "Airline");
        List<String> actualRoles = UserTypeUtils.getRoleList(userType);
        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    public void testGetRoleListALL() {
        int userType = UserType.ALL;
        List<String> expectedRoles = Arrays.asList("Passenger", "Merchant", "Airline", "Staff");
        List<String> actualRoles = UserTypeUtils.getRoleList(userType);
        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    public void testGetRoleCount() {
        int userType = UserType.PASSENGER | UserType.MERCHANT | UserType.AIRLINE;
        int expectedCount = 3;
        int actualCount = UserTypeUtils.getRoleCount(userType);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testGetRoleCountALL() {
        int userType = UserType.ALL;
        int expectedCount = 4;
        int actualCount = UserTypeUtils.getRoleCount(userType);
        assertEquals(expectedCount, actualCount);
    }

}