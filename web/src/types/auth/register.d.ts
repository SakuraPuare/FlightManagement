/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

export type BaseRegisterParams = {
  username: string;
  password: string;
  email: string;
};

export type RegisterParams<T> = BaseRegisterParams & {
  data: T;
};
