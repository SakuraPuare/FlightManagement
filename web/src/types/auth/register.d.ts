export type BaseRegisterParams = {
  username: string;
  password: string;
  email: string;
};

export type RegisterParams<T> = BaseRegisterParams & {
  data: T;
};
