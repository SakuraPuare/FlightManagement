export type AllCountStatisticResponse = {
  airlineCount: number;
  merchantCount: number;
  passengerCount: number;
  staffCount: number;
  userCount: number;
  flightCount: number;
  goodsCount: number;
  luggageCount: number;
  orderCount: number;
  requestCount: number;
  ticketCount: number;
};

export type GoodsCountStatisticResponse = {
  goodsCount: number;
  goodsTotalPrice: number;
};

export type TypesStatistic =
  | "airline"
  | "merchant"
  | "passenger"
  | "staff"
  | "user"
  | "flight"
  | "goods"
  | "luggage"
  | "order"
  | "request"
  | "ticket";
