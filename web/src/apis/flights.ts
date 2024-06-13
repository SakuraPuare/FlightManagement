import {
  createFlightRequest,
  Flight,
  updateFlightRequest,
} from "@/types/flight";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getFlightAPI = async (id: number): Promise<Flight> => {
  return http.get(`/flights/${id}`).then((res) => res.data as Flight);
};

export const getFlightsListAPI = async (
  params: Pagination
): Promise<Flight[]> => {
  return http.get("/flights/", { params }).then((res) => res.data as Flight[]);
};

export const createFlightAPI = async (
  params: createFlightRequest
): Promise<void> => {
  return http.post("/flights/", params).then((res) => res.data);
};

export const updateFlightAPI = async (
  id: number,
  params: updateFlightRequest
): Promise<void> => {
  return http.put(`/flights/${id}`, params).then((res) => res.data);
};

export const deleteFlightAPI = async (id: number): Promise<void> => {
  return http.delete(`/flights/${id}`).then((res) => res.data);
};

export const searchFlightAPI = async (query: string): Promise<Flight[]> => {
  return http
    .get(`/flights/search?query=${query}`)
    .then((res) => res.data as Flight[]);
};
