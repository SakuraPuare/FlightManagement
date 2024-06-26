/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { getOrderResponse } from "@/types/order";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getOrderAPI = async (id: number): Promise<getOrderResponse> => {
  return http.get(`/orders/${id}`).then((res) => res.data as getOrderResponse);
};

export const getOrdersListAPI = async (
  params: Pagination,
): Promise<getOrderResponse[]> => {
  return http
    .get("/orders/list", { params })
    .then((res) => res.data as getOrderResponse[]);
};

export const getMyOrdersListAPI = async (
  params: Pagination,
): Promise<getOrderResponse[]> => {
  return http
    .get("/orders/my", { params })
    .then((res) => res.data as getOrderResponse[]);
};

export const getOrdersListByUserIdAPI = async (
  id: number,
): Promise<getOrderResponse[]> => {
  return http
    .get(`/users/user/${id}/orders`)
    .then((res) => res.data as getOrderResponse[]);
};

export const getOrdersListByPassengerIdAPI = async (
  id: number,
): Promise<getOrderResponse[]> => {
  return http
    .get(`/users/passenger/${id}/orders`)
    .then((res) => res.data as getOrderResponse[]);
};

export const createOrderAPI = async (id: number): Promise<string> => {
  return http.post(`/orders/${id}`).then((res) => res.data);
};

export const deleteOrderAPI = async (id: number): Promise<string> => {
  return http.delete(`/orders/${id}`).then((res) => res.data);
};

export const payOrderAPI = async (id: number): Promise<string> => {
  return http.post(`/orders/${id}/pay`).then((res) => res.data);
};

export const cancelOrderAPI = async (id: number): Promise<string> => {
  return http.post(`/orders/${id}/cancel`).then((res) => res.data);
};
