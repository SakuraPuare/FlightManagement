/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

export type AirlineResponse = {
  airlineName: string;
  airlineCode: string;
  airlineCountry: string;
  airlineDescription: string;
  createdAt: string;
  updatedAt: string;
};

export type MerchantResponse = {
  merchantId: number;
  userId: number;
  merchantName: string;
  merchantAddress: string;
  merchantDescription: string;
  createdAt: string;
  updatedAt: string;
};

export type PassengerResponse = {
  passengerId: number;
  userId: number;
  passengerName: string;
  passengerPhone: string;
  identityNumber: string;
  createdAt: string;
  updatedAt: string;
};

export type StaffResponse = {
  staffId: number;
  userId: number;
  staffName: string;
  createdAt: string;
  updatedAt: string;
};
