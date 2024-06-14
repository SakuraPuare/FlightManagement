import {
  createLuggageRequest,
  getLuggageResponse,
  updateLuggageRequest,
} from "@/types/luggage";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getLuggageAPI = async (
  id: number,
): Promise<getLuggageResponse> => {
  return http
    .get(`/luggages/${id}`)
    .then((res) => res.data as getLuggageResponse);
};

export const getLuggagesListAPI = async (
  params: Pagination,
): Promise<getLuggageResponse[]> => {
  return http
    .get("/luggages/list", { params })
    .then((res) => res.data as getLuggageResponse[]);
};

export const getMyLuggageListAPI = async (
  params: Pagination,
): Promise<getLuggageResponse[]> => {
  return http
    .get("/luggages/my", { params })
    .then((res) => res.data as getLuggageResponse[]);
};

export const createLuggageAPI = async (
  params: createLuggageRequest,
): Promise<void> => {
  return http.post("/luggages/", params).then((res) => res.data);
};

export const updateLuggageAPI = async (
  id: number,
  params: updateLuggageRequest,
): Promise<void> => {
  return http.put(`/luggages/${id}`, params).then((res) => res.data);
};

export const deleteLuggageAPI = async (id: number): Promise<void> => {
  return http.delete(`/luggages/${id}`).then((res) => res.data);
};
