export type Order = {
  userId: number;
  ticketId: number;
  status: string;
  createdAt: string;
  updatedAt: string;
};

export type getOrderResponse = Id & Order;

export type updateOrderRequest = Partial<Order>;
