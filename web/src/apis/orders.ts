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
    .get("/orders/list", { params })
    .then((res) => res.data as getOrderResponse[]);
};

export const createOrderAPI = async (id: number): Promise<null> => {
  return http.post(`/orders/${id}`).then((res) => res.data);
};

export const deleteOrderAPI = async (id: number): Promise<null> => {
  return http.delete(`/orders/${id}`).then((res) => res.data);
};

export const payOrderAPI = async (id: number): Promise<null> => {
  return http.post(`/orders/${id}/pay`).then((res) => res.data);
};

export const cancelOrderAPI = async (id: number): Promise<null> => {
  return http.post(`/orders/${id}/cancel`).then((res) => res.data);
};
