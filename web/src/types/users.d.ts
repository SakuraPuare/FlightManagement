/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

export type User = {
  userId: number;
  username: string;
  email: string;
  role: number;
  createdAt: string;
  updatedAt: string;
};

export type Airline = {
  airlineId: number;
  userId: number;
  airlineName: string;
  airlineCountry: string;
  airlineCode: string;
  airlineDescription: string;
  createdAt: string;
  updatedAt: string;
};

export type Merchant = {
  merchantId: number;
  userId: number;
  merchantName: string;
  merchantAddress: string;
  merchantDescription: string;
  createdAt: string;
  updatedAt: string;
};

export type Passenger = {
  passengerId: number;
  userId: number;
  passengerName: string;
  passengerPhone: string;
  identityNumber: string;
  createdAt: string;
  updatedAt: string;
};

export type Staff = {
  staffId: number;
  userId: number;
  staffName: string;
  createdAt: string;
  updatedAt: string;
};
