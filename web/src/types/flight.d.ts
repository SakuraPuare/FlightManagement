import { Id } from "./types";

export type Flight = {
  flightNumber: string;
  capacity: number;
  departureCity: string;
  arrivalCity: string;
  dateOfDeparture: string;
  estimatedTravelTime: number;
};

export type getFlightResponse = Id & {
  userId: number;
  airlineId: number;
  airlineName: string;
  airlineCountry: string;
  airlineCode: string;
  airlineDescription: string;
  flightNumber: string;
  capacity: number;
  departureCity: string;
  arrivalCity: string;
  dateOfDeparture: string;
  estimatedTravelTime: number;
  createdAt: string;
  updatedAt: string;
};

export type createFlightRequest = Flight;

export type updateFlightRequest = Flight;
