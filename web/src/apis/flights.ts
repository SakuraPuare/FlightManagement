/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { createFlightRequest, Flight, getFlightResponse, updateFlightRequest } from "@/types/flight";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getFlightAPI = async (id: number): Promise<Flight> => {
  return http.get(`/flights/${id}`).then((res) => res.data as Flight);
};

export const getFlightsListAPI = async (
  params: Pagination,
): Promise<getFlightResponse[]> => {
  return http
    .get("/flights/list", { params })
    .then((res) => res.data as getFlightResponse[]);
};

export const getMyFlightsListAPI = async (
  params: Pagination,
): Promise<getFlightResponse[]> => {
  return http
    .get("/flights/my", { params })
    .then((res) => res.data as getFlightResponse[]);
};

export const createFlightAPI = async (
  params: createFlightRequest,
): Promise<string> => {
  return http.post("/flights/", params).then((res) => res.data);
};

export const updateFlightAPI = async (
  id: number,
  params: updateFlightRequest,
): Promise<string> => {
  return http.put(`/flights/${id}`, params).then((res) => res.data);
};

export const deleteFlightAPI = async (id: number): Promise<string> => {
  return http.delete(`/flights/${id}`).then((res) => res.data);
};

export const searchFlightAPI = async (query: string): Promise<Flight[]> => {
  return http
    .get(`/flights/search?query=${query}`)
    .then((res) => res.data as Flight[]);
};
