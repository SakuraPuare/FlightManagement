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
    path: "/home",
    component: () => import("@/views/index/HomeView.vue"),
    meta: { title: "Home", requiresAuth: true },
    children: [
      {
        path: "/home/passgenger",
        meta: { title: "Passenger", requiresAuth: true },
        component: { render: () => null },
        children: [
          {
            path: "/home/passenger/ticket",
            component: () => import("@/views/home/passenger/TicketView.vue"),
            meta: { title: "Ticket", requiresAuth: true },
          },
          {
            path: "/home/passenger/order",
            component: () => import("@/views/home/passenger/OrderView.vue"),
            meta: { title: "Order", requiresAuth: true },
          },
          {
            path: "/home/passenger/goods",
            component: () => import("@/views/home/passenger/GoodsView.vue"),
            meta: { title: "Goods", requiresAuth: true },
          },
          {
            path: "/home/passenger/luggage",
            component: () => import("@/views/home/passenger/LuggageView.vue"),
            meta: { title: "Luggage", requiresAuth: true },
          },
        ],
      },
      {
        path: "/home/merchant",
        // return empty component
        component: { render: () => null },
        meta: { title: "Merchant", requiresAuth: true },
        children: [
          {
            // goods
            path: "/home/merchant/goods",
            component: () => import("@/views/home/merchant/GoodsView.vue"),
            meta: { title: "Goods", requiresAuth: true },
          },
        ],
      },
      {
        path: "/home/airline",
        meta: { title: "Airline", requiresAuth: true },
        component: { render: () => null },
        children: [
          {
            path: "/home/airline/order",
            component: () => import("@/views/home/airline/OrderView.vue"),
            meta: { title: "Order", requiresAuth: true },
          },
          {
            path: "/home/airline/ticket",
            component: () => import("@/views/home/airline/TicketView.vue"),
            meta: { title: "Ticket", requiresAuth: true },
          },
          {
            path: "/home/airline/flight",
            component: () => import("@/views/home/airline/FlightView.vue"),
            meta: { title: "Flight", requiresAuth: true },
          },
        ],
      },
      {
        path: "/home/staff",
        meta: { title: "Staff", requiresAuth: true },
        component: { render: () => null },
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

router.beforeEach((to, _, next) => {
  const user = useUserStore();
  const needLogin = to.matched.some((record) => record.meta?.requiresAuth);
  // console.log("needLogin", needLogin);
  if (needLogin) {
    const isLogin = user.isLogin();
    // console.log("isLogin", isLogin);
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
