import { LoginParams, LoginResponse } from "@/types/auth/login";
import http from "../utils/http";
import { BaseRegisterParams } from "@/types/auth/register";

export const loginAPI = async (params: LoginParams): Promise<LoginResponse> => {
  return http.post("/login", params).then((res) => res.data as LoginResponse);
};

export const registerUserAPI = async (
  params: BaseRegisterParams
): Promise<void> => {
  return http.post("/register", params).then((res) => res.data);
};

export const registerAirlineAPI = async (
  params: BaseRegisterParams
): Promise<void> => {
  return http.post("/register/airline", params).then((res) => res.data);
};

export const registerMerchantAPI = async (
  params: BaseRegisterParams
): Promise<void> => {
  return http.post("/register/merchant", params).then((res) => res.data);
};

export const registerPassengerAPI = async (
  params: BaseRegisterParams
): Promise<void> => {
  return http.post("/register/passenger", params).then((res) => res.data);
};

export const registerStaffAPI = async (
  params: BaseRegisterParams
): Promise<void> => {
  return http.post("/register/staff", params).then((res) => res.data);
};
