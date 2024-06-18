import {
  createRequestRequest,
  getRequestResponse,
  updateRequestRequest,
} from "@/types/request";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getRequestsAPI = async (
  id: number,
): Promise<getRequestResponse> => {
  return http
    .get(`/requests/${id}`)
    .then((res) => res.data as getRequestResponse);
};

export const getRequestsListAPI = async (
  params: Pagination,
): Promise<getRequestResponse[]> => {
  return http
    .get("/requests/list", { params })
    .then((res) => res.data as getRequestResponse[]);
};

export const createRequestAPI = async (
  params: createRequestRequest,
): Promise<null> => {
  return http.post(`/requests/`, params).then((res) => res.data);
};

export const updateRequestAPI = async (
  id: number,
  params: updateRequestRequest,
): Promise<null> => {
  return http.put(`/requests/${id}`, params).then((res) => res.data);
};

export const deleteRequestAPI = async (id: number): Promise<null> => {
  return http.delete(`/requests/${id}`).then((res) => res.data);
};

export const handleRequestAPI = async (id: number): Promise<null> => {
  return http.post(`/requests/${id}/handle`).then((res) => res.data);
};

export const resolveRequestAPI = async (id: number): Promise<null> => {
  return http.post(`/requests/${id}/resolve`).then((res) => res.data);
};

export const cancelRequestAPI = async (id: number): Promise<null> => {
  return http.post(`/requests/${id}/cancel`).then((res) => res.data);
};
