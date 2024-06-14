import { LoginResponse } from "@/types/auth/login";
import { defineStore } from "pinia";

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    userId: 0,
    username: "",
    password: "",
    email: "",
    role: 0,
    token: {} as LoginResponse,
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
  },
});
