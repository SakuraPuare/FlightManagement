export type Ticket = {
  flightId: number;
  seatClass: string;
  quota: number;
  price: string;
  createdAt: string;
  updatedAt: string;
};

export type getTicketResponse = Id & Ticket;

export type createTicketRequest = {
  flightId: number;
  seatClass: string;
  quota: number;
  price: string;
};

export type updateTicketRequest = Partial<createTicketRequest>;
