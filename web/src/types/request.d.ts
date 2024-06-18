export type Request = {
  userId: number;
  info: string;
  status: string;
  handlerId: number;
  createdAt: string;
  updatedAt: string;
};

export type getRequestResponse = Id & Request;

export type createRequestRequest = {
  info: string;
};

export type updateRequestRequest = createRequestRequest;
