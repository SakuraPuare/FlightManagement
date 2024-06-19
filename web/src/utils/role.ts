/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { Role } from "@/constant/role";

export const getUserRoleList = (role: number): string[] => {
  const roleList = [];
  if (role & Role.PASSENGER) {
    roleList.push("Passenger");
  }
  if (role & Role.MERCHANT) {
    roleList.push("Merchant");
  }
  if (role & Role.AIRLINE) {
    roleList.push("Airline");
  }
  if (role & Role.STAFF) {
    roleList.push("Staff");
  }
  return roleList;
};

export const isPassenger = (role: number): boolean => {
  return !!(role & Role.PASSENGER);
};

export const isMerchant = (role: number): boolean => {
  return !!(role & Role.MERCHANT);
};

export const isAirline = (role: number): boolean => {
  return !!(role & Role.AIRLINE);
};

export const isStaff = (role: number): boolean => {
  return !!(role & Role.STAFF);
};
