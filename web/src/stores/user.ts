import { LoginResponse } from "@/types/auth/login";
import { User } from "@/types/users";
import { getUserRoleList } from "@/utils/role";
import { defineStore } from "pinia";

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    userId: 0,
    username: "",
    password: "",
    email: "",
    role: 0,
    currentRole: "",
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
      this.token = {
        token: "",
        expiresTime: "",
      } as LoginResponse;
    },
    isLogin() {
      return this.token.token !== "";
    },
    logout() {
      this.userId = 0;
      this.username = "";
      this.email = "";
      this.role = 0;
      this.currentRole = "";
      this.createdAt = "";
      this.updatedAt = "";
      this.clearToken();
    },
    setUser(data: User) {
      this.userId = data.userId;
      this.username = data.username;
      this.email = data.email;
      this.role = data.role;
      this.currentRole = getUserRoleList(this.role)[0];
      this.createdAt = data.createdAt;
      this.updatedAt = data.updatedAt;
    },
  },
  persist: true,
});
