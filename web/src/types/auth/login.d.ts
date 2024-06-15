export type LoginParams = {
  username: string;
  password: string;
};

export type LoginResponse = UserInfo & {
  token: string;
  expiresTime: string;
};
