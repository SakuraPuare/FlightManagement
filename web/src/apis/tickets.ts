import { createTicketRequest, getTicketResponse } from "@/types/ticket";
import http from "@/utils/http";

export const getTicketAPI = async (id: number): Promise<getTicketResponse> => {
  return http
    .get(`/tickets/${id}`)
    .then((res) => res.data as getTicketResponse);
};

export const getTicketsListAPI = async (): Promise<getTicketResponse[]> => {
  return http
    .get("/tickets/list")
    .then((res) => res.data as getTicketResponse[]);
};

export const createTicketAPI = async (
  params: createTicketRequest,
): Promise<void> => {
  return http.post("/tickets/", params).then((res) => res.data);
};

export const updateTicketAPI = async (
  id: number,
  params: createTicketRequest,
): Promise<void> => {
  return http.put(`/tickets/${id}`, params).then((res) => res.data);
};

export const deleteTicketAPI = async (id: number): Promise<void> => {
  return http.delete(`/tickets/${id}`).then((res) => res.data);
};
