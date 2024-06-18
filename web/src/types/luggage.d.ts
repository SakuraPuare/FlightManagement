export type Luggage = {
  userId: number;
  orderId: number;
  weight: number;
  staffId: number;
  createdAt: string;
  updatedAt: string;
};

export type getLuggageResponse = Id & Luggage;

export type createLuggageRequest = {
  userId: number;
  orderId: number;
  weight: number;
};

export type updateLuggageRequest = createLuggageRequest;
