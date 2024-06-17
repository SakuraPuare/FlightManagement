export type Order = {
  userId: number;
  ticketId: number;
  status: string;
  createdAt: string;
  updatedAt: string;
};

export type getOrderResponse = {
  id: number;
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

export type updateOrderRequest = Partial<Order>;
