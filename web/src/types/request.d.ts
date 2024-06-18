export type Request = {
  userId: number;
  userName: string;
  info: string;
  status: string;
  handlerId: number;
  handlerName: string;
  createdAt: string;
  updatedAt: string;
};

export type getRequestResponse = Id & Request;

export type createRequestRequest = {
  info: string;
};

export type updateRequestRequest = createRequestRequest;
