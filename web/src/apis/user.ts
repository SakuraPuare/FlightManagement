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
