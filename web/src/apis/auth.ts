/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { LoginParams, LoginResponse } from "@/types/auth/login";
import http from "../utils/http";
import { BaseRegisterParams, RegisterParams } from "@/types/auth/register";
import { AirlineInfo, MerchantInfo, PassengerInfo, StaffInfo } from "@/types/auth/info";

export const loginAPI = async (params: LoginParams): Promise<LoginResponse> => {
  return http.post("/login", params).then((res) => res.data as LoginResponse);
};

export const registerUserAPI = async (
  params: BaseRegisterParams,
): Promise<string> => {
  return http.post("/register", params).then((res) => res.data);
};

export const registerAirlineAPI = async (
  params: RegisterParams<AirlineInfo>,
): Promise<string> => {
  return http.post("/register/airline", params).then((res) => res.data);
};

export const registerMerchantAPI = async (
  params: RegisterParams<MerchantInfo>,
): Promise<string> => {
  return http.post("/register/merchant", params).then((res) => res.data);
};

export const registerPassengerAPI = async (
  params: RegisterParams<PassengerInfo>,
): Promise<string> => {
  return http.post("/register/passenger", params).then((res) => res.data);
};

export const registerStaffAPI = async (
  params: RegisterParams<StaffInfo>,
): Promise<string> => {
  return http.post("/register/staff", params).then((res) => res.data);
};
