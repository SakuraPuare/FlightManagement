/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

export type Order = {
  userId: number;
  ticketId: number;
  status: string;
  createdAt: string;
  updatedAt: string;
};

export type getOrderResponse = Id & {
  airlineName: string;
  flightNumber: string;
  departureCity: string;
  arrivalCity: string;
  dateOfDeparture: string;
  estimatedTravelTime: number;
  seatClass: string;
  price: string;
  status: string;
  createdAt: string;
  updatedAt: string;
};

export type updateOrderRequest = Order;
