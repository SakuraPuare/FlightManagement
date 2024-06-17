import { useUserStore } from "@/stores/user";

import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    path: "/",
    component: () => import("@/views/index/PublicView.vue"),
    meta: { title: "Public" },
  },
  {
    path: "/:path(about|public)?",
    component: () => import("@/views/index/PublicView.vue"),
    meta: { title: "Public" },
  },
  {
    path: "/users",
    component: () => import("@/views/index/PageView.vue"),
    meta: { title: "User" },
    children: [
      {
        path: ":id(\\d+)",
        component: () => import("@/views/user/UserView.vue"),
      },
    ],
  },
  {
    path: "/home/",
    component: () => import("@/views/index/HomeView.vue"),
    meta: { title: "Home", requiresAuth: true },
    children: [
      {
        path: "/goods",
        component: () => import("@/views/home/GoodsView.vue"),
        meta: { title: "Goods", requiresAuth: true },
      },
      {
        path: "/luggage",
        component: () => import("@/views/home/LuggageView.vue"),
        meta: { title: "Luggage", requiresAuth: true },
      },
      {
        path: "/ticket",
        component: () => import("@/views/home/TicketView.vue"),
        meta: { title: "Ticket", requiresAuth: true },
      },
      {
        path: "/order",
        component: () => import("@/views/home/OrderView.vue"),
        meta: { title: "Order", requiresAuth: true },
      },
      {
        path: "/home/airline",
        component: () => import("@/views/home/management/AirlineView.vue"),
        meta: { title: "Airline", requiresAuth: true },
      },
      {
        path: "/home/merchant",
        component: () => import("@/views/home/management/MerchantView.vue"),
        meta: { title: "Merchant", requiresAuth: true },
      },
      {
        path: "/home/passenger",
        component: () => import("@/views/home/management/PassengerView.vue"),
        meta: { title: "Passenger", requiresAuth: true },
      },
      {
        path: "/home/staff",
        component: () => import("@/views/home/management/StaffView.vue"),
        meta: { title: "Staff", requiresAuth: true },
      },
    ],
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
    path: "/403",
    component: () => import("@/pages/ForbiddenPage.vue"),
    meta: { title: "Forbidden" },
  },
  // {
  //   path: "/:pathMatch(.*)*",
  //   component: () => import("@/pages/NotFoundPage.vue"),
  //   meta: { title: "Not Found" },
  // },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach((to, _, next) => {
  const user = useUserStore();
  const needLogin = to.matched.some((record) => record.meta?.requiresAuth);
  console.log("needLogin", needLogin);
  if (needLogin) {
    const isLogin = user.isLogin();
    console.log("isLogin", isLogin);
    if (isLogin) {
      next();
    } else {
      next("/403");
    }
  } else {
    next();
  }
});

export default router;
