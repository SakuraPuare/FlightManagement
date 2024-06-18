export type Ticket = {
  flightId: number;
  seatClass: string;
  quota: number;
  price: string;
  createdAt: string;
  updatedAt: string;
};

export type getTicketResponse = Id & {
  airlineId: number;
  flightId: number;
  airlineName: string;
  airlineCountry: string;
  flightNumber: string;
  capacity: number;
  departureCity: string;
  arrivalCity: string;
  dateOfDeparture: string;
  estimatedTravelTime: number;
  seatClass: string;
  quota: number;
  price: string;
};

export type createTicketRequest = {
  flightId: number;
  seatClass: string;
  quota: number;
  price: string;
};

export type updateTicketRequest = createTicketRequest;
