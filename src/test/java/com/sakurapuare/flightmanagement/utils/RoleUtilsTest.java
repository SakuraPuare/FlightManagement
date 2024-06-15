package com.sakurapuare.flightmanagement.utils;

import com.sakurapuare.flightmanagement.constant.Role;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoleUtilsTest {

    @Test
    public void testIsContain() {
        int userType = Role.PASSENGER | Role.MERCHANT;
        assertTrue(RoleUtils.isContain(userType, Role.PASSENGER));
        assertTrue(RoleUtils.isContain(userType, Role.MERCHANT));
        assertFalse(RoleUtils.isContain(userType, Role.AIRLINE));
        assertFalse(RoleUtils.isContain(userType, Role.STAFF));
    }

    @Test
    public void testIsPassenger() {
        int userType = Role.PASSENGER;
        assertTrue(RoleUtils.isPassenger(userType));
        assertFalse(RoleUtils.isPassenger(Role.MERCHANT));
    }

    @Test
    public void testIsMerchant() {
        int userType = Role.MERCHANT;
        assertTrue(RoleUtils.isMerchant(userType));
        assertFalse(RoleUtils.isMerchant(Role.PASSENGER));
    }

    @Test
    public void testIsAirline() {
        int userType = Role.AIRLINE;
        assertTrue(RoleUtils.isAirline(userType));
        assertFalse(RoleUtils.isAirline(Role.PASSENGER));
    }

    @Test
    public void testIsStaff() {
        int userType = Role.STAFF;
        assertTrue(RoleUtils.isStaff(userType));
        assertFalse(RoleUtils.isStaff(Role.PASSENGER));
    }

    @Test
    public void testIsAll() {
        int userType = Role.ALL;
        assertTrue(RoleUtils.isAll(userType));
        assertFalse(RoleUtils.isAll(Role.PASSENGER));
    }

    @Test
    public void testAddRole() {
        int userType = Role.PASSENGER;
        userType = RoleUtils.addRole(userType, Role.MERCHANT);
        assertTrue(RoleUtils.isMerchant(userType));
    }

    @Test
    public void testAddPassenger() {
        int userType = Role.MERCHANT;
        userType = RoleUtils.addPassenger(userType);
        assertTrue(RoleUtils.isPassenger(userType));
    }

    @Test
    public void testAddMerchant() {
        int userType = Role.PASSENGER;
        userType = RoleUtils.addMerchant(userType);
        assertTrue(RoleUtils.isMerchant(userType));
    }

    @Test
    public void testAddAirline() {
        int userType = Role.PASSENGER;
        userType = RoleUtils.addAirline(userType);
        assertTrue(RoleUtils.isAirline(userType));
    }

    @Test
    public void testAddStaff() {
        int userType = Role.PASSENGER;
        userType = RoleUtils.addStaff(userType);
        assertTrue(RoleUtils.isStaff(userType));
    }

    @Test
    public void testRemovePassenger() {
        int userType = Role.PASSENGER | Role.MERCHANT;
        userType = RoleUtils.removePassenger(userType);
        assertFalse(RoleUtils.isPassenger(userType));
        assertTrue(RoleUtils.isMerchant(userType));
    }

    @Test
    public void testRemoveMerchant() {
        int userType = Role.PASSENGER | Role.MERCHANT;
        userType = RoleUtils.removeMerchant(userType);
        assertFalse(RoleUtils.isMerchant(userType));
        assertTrue(RoleUtils.isPassenger(userType));
    }

    @Test
    public void testRemoveAirline() {
        int userType = Role.PASSENGER | Role.AIRLINE;
        userType = RoleUtils.removeAirline(userType);
        assertFalse(RoleUtils.isAirline(userType));
        assertTrue(RoleUtils.isPassenger(userType));
    }

    @Test
    public void testRemoveStaff() {
        int userType = Role.PASSENGER | Role.STAFF;
        userType = RoleUtils.removeStaff(userType);
        assertFalse(RoleUtils.isStaff(userType));
        assertTrue(RoleUtils.isPassenger(userType));
    }

    @Test
    public void testGetRoleList() {
        int userType = Role.PASSENGER | Role.MERCHANT | Role.AIRLINE;
        List<String> expectedRoles = Arrays.asList("Passenger", "Merchant", "Airline");
        List<String> actualRoles = RoleUtils.getRoleList(userType);
        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    public void testGetRoleListALL() {
        int userType = Role.ALL;
        List<String> expectedRoles = Arrays.asList("Passenger", "Merchant", "Airline", "Staff");
        List<String> actualRoles = RoleUtils.getRoleList(userType);
        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    public void testGetRoleCount() {
        int userType = Role.PASSENGER | Role.MERCHANT | Role.AIRLINE;
        int expectedCount = 3;
        int actualCount = RoleUtils.getRoleCount(userType);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testGetRoleCountALL() {
        int userType = Role.ALL;
        int expectedCount = 4;
        int actualCount = RoleUtils.getRoleCount(userType);
        assertEquals(expectedCount, actualCount);
    }

}