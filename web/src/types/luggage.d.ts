/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

export type Luggage = {
  userId: number;
  orderId: number;
  weight: number;
  staffId: number;
  createdAt: string;
  updatedAt: string;
};

export type getLuggageResponse = Id & {
  userId: number;
  username: string;
  orderId: number;
  weight: number;
  staffId: number;
  staffName: string;
  createdAt: string;
  updatedAt: string;
};

export type createLuggageRequest = {
  userId: number;
  orderId: number;
  weight: number;
};

export type updateLuggageRequest = createLuggageRequest;
