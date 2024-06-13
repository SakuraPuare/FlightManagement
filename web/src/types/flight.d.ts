import { Id } from "./types";

export type Flight = {
  airlineId: number;
  flightNumber: string;
  capacity: number;
  departureCity: string;
  arrivalCity: string;
  dateOfDeparture: string;
  estimatedTravelTime: number;
};

export type getFlightResponse = Id & Flight;

export type createFlightRequest = Flight;

export type updateFlightRequest = Partial<Flight>;
