import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    path: "/",
    component: () => import("@/views/index/PageView.vue"),
    meta: { title: "Home" },
    children: [
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
    ],
  },
  {
    path: "/public",
    component: () => import("@/views/index/PublicView.vue"),
    meta: { title: "Public" },
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
