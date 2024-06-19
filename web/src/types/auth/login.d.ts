/*
 * Copyright (c) 2024. All right reserved by SakuraPuare.
 */

export type LoginParams = {
  username: string;
  password: string;
};

export type LoginResponse = UserInfo & {
  token: string;
  expiresTime: string;
};
