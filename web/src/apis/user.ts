/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { Pagination } from "@/types/types";
import { Airline, Merchant, Passenger, Staff, User } from "@/types/users";
import http from "@/utils/http";

export const getUserAPI = async (userId: number): Promise<User> => {
  return http.get(`/users/user/${userId}`).then((res) => res.data as User);
};

export const getAirlineAPI = async (userId: number): Promise<Airline> => {
  return http
    .get(`/users/airline/${userId}`)
    .then((res) => res.data as Airline);
};

export const getMerchantAPI = async (userId: number): Promise<Merchant> => {
  return http
    .get(`/users/merchant/${userId}`)
    .then((res) => res.data as Merchant);
};

export const getPassengerAPI = async (userId: number): Promise<Passenger> => {
  return http
    .get(`/users/passenger/${userId}`)
    .then((res) => res.data as Passenger);
};

export const getStaffAPI = async (userId: number): Promise<Staff> => {
  return http.get(`/users/staff/${userId}`).then((res) => res.data as Staff);
};

export const getUsersListAPI = async (params: Pagination): Promise<User[]> => {
  return http
    .get("/users/user/list", { params })
    .then((res) => res.data as User[]);
};
export const getAirlinesListAPI = async (
  params: Pagination,
): Promise<Airline[]> => {
  return http
    .get("/users/airline/list", { params })
    .then((res) => res.data as Airline[]);
};

export const getMerchantsListAPI = async (
  params: Pagination,
): Promise<Merchant[]> => {
  return http
    .get("/users/merchant/list", { params })
    .then((res) => res.data as Merchant[]);
};

export const getPassengersListAPI = async (
  params: Pagination,
): Promise<Passenger[]> => {
  return http
    .get("/users/passenger/list", { params })
    .then((res) => res.data as Passenger[]);
};

export const getStaffListAPI = async (params: Pagination): Promise<Staff[]> => {
  return http
    .get("/users/staff/list", { params })
    .then((res) => res.data as Staff[]);
};
