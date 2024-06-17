import { createTicketRequest, getTicketResponse } from "@/types/ticket";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getTicketAPI = async (id: number): Promise<getTicketResponse> => {
  return http
    .get(`/tickets/${id}`)
    .then((res) => res.data as getTicketResponse);
};

export const getTicketsListAPI = async (
  params: Pagination,
): Promise<getTicketResponse[]> => {
  return http
    .get("/tickets/list", { params })
    .then((res) => res.data as getTicketResponse[]);
};

export const createTicketAPI = async (
  params: createTicketRequest,
): Promise<null> => {
  return http.post("/tickets/", params).then((res) => res.data);
};

export const updateTicketAPI = async (
  id: number,
  params: createTicketRequest,
): Promise<null> => {
  return http.put(`/tickets/${id}`, params).then((res) => res.data);
};

export const deleteTicketAPI = async (id: number): Promise<null> => {
  return http.delete(`/tickets/${id}`).then((res) => res.data);
};
