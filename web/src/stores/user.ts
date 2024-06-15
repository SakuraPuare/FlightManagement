import { LoginResponse } from "@/types/auth/login";
import { User } from "@/types/user";
import { defineStore } from "pinia";

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    userId: 0,
    username: "",
    password: "",
    email: "",
    role: 0,
    token: {
      token: "",
      expiresTime: "",
    } as LoginResponse,
    createdAt: "",
    updatedAt: "",
  }),
  getters: {},
  actions: {
    setToken(token: LoginResponse) {
      this.token = token;
    },
    clearToken() {
      this.token = {} as LoginResponse;
    },
    isLogin() {
      return this.token.token !== "";
    },
    logout() {
      this.clearToken();
    },
    setUser(data: User) {
      this.userId = data.userId;
      this.username = data.username;
      this.password = data.password;
      this.email = data.email;
      this.role = data.role;
      this.createdAt = data.createdAt;
      this.updatedAt = data.updatedAt;
    },
  },
  persist: true,
});
