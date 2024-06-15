export type UserInfo = {
  userId: number;
  username: string;
  email: string;
  role: number;
  createdAt: string;
  updatedAt: string;
};

export type AirlineInfo = {
  airlineName: string;
  airlineCode: string;
  airlineCountry: string;
  airlineDescription: string;
};

export type MerchantInfo = {
  merchantName: string;
  merchantAddress: string;
  merchantDescription: string;
};

export type PassengerInfo = {
  passengerName: string;
  passengerPhone: string;
  identityNumber: string;
};

export type StaffInfo = {
  staffName: string;
};
