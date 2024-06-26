/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

import { createGoodsRequest, getGoodsResponse, updateGoodsRequest } from "@/types/goods";
import { Pagination } from "@/types/types";
import http from "@/utils/http";

export const getGoodsAPI = async (id: number): Promise<getGoodsResponse> => {
  return http.get(`/goods/${id}`).then((res) => res.data as getGoodsResponse);
};

export const getGoodsListAPI = async (
  params: Pagination,
): Promise<getGoodsResponse[]> => {
  return http
    .get("/goods/list", { params })
    .then((res) => res.data as getGoodsResponse[]);
};

export const getMyGoodsListAPI = async (
  params: Pagination,
): Promise<getGoodsResponse[]> => {
  return http
    .get("/goods/my", { params })
    .then((res) => res.data as getGoodsResponse[]);
};

export const createGoodsAPI = async (
  params: createGoodsRequest,
): Promise<string> => {
  return http.post("/goods/", params).then((res) => res.data);
};

export const updateGoodsAPI = async (
  id: number,
  params: updateGoodsRequest,
): Promise<string> => {
  return http.put(`/goods/${id}`, params).then((res) => res.data);
};

export const deleteGoodsAPI = async (id: number): Promise<string> => {
  return http.delete(`/goods/${id}`).then((res) => res.data);
};

export const searchGoodsAPI = async (
  query: string,
): Promise<getGoodsResponse[]> => {
  return http
    .get(`/goods/search?query=${query}`)
    .then((res) => res.data as getGoodsResponse[]);
};

export const buyGoodsAPI = async (id: number): Promise<string> => {
  return http.post(`/goods/${id}/buy`).then((res) => res.data);
};
