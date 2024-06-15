import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    path: "/",
    component: () => import("@/views/index/PageView.vue"),
    meta: { title: "Home" },
    children: [
      {
        path: "/users/:id(\\d+)",
        component: () => import("@/views/user/UserView.vue"),
        meta: { title: "User" },
      },
    ],
  },
  {
    path: "/public",
    component: () => import("@/views/index/PublicView.vue"),
    meta: { title: "Public" },
  },
  {
    path: "/login",
    component: () => import("@/views/auth/LoginView.vue"),
    meta: { title: "Login" },
  },
  {
    path: "/register",
    component: () => import("@/views/auth/RegisterView.vue"),
    meta: { title: "Register" },
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/pages/NotFoundPage.vue"),
    meta: { title: "Not Found" },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
