<script lang="ts" setup>
import HeaderComp from "@/components/HeaderComp.vue";
import { useUserStore } from "@/stores/user";
import {
  getUserRoleList,
  isAirline,
  isMerchant,
  isPassenger,
  isStaff,
} from "@/utils/role";

import {
  faBuilding,
  faGear,
  faHome,
  faPlaneDeparture,
  faStore,
  faSuitcase,
  faTicket,
  faUser,
  faUserTie,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { autoIncrement } from "@/utils/utils";
import { onMounted, ref, watch } from "vue";
import { getHeightWithoutHeader } from "@/utils/responsive";
import router from "@/utils/router";
import { MenuItem } from "@/types/types";

// if path = /home
const isAtHomePath = ref(router.currentRoute.value.path === "/home");
watch(
  () => router.currentRoute.value.path,
  () => {
    if (router.currentRoute.value.path === "/home") {
      isAtHomePath.value = true;
      console.log("home");
    } else {
      isAtHomePath.value = false;
    }
  },
);

const user = useUserStore();

const menuItems: MenuItem[] = [
  {
    id: 1,
    label: "Home",
    link: "/home",
    icon: faHome,
    children: [],
  },
  {
    id: 2,
    label: "Ticket",
    link: "/ticket",
    icon: faTicket,
    children: [],
  },
  {
    id: 3,
    label: "Order",
    link: "/order",
    icon: faPlaneDeparture,
    children: [],
  },
  {
    id: 4,
    label: "Goods",
    link: "/goods",
    icon: faStore,
    children: [],
  },
  {
    id: 5,
    label: "Luggage",
    link: "/luggage",
    icon: faSuitcase,
    children: [],
  },
];

let childrenItem: MenuItem[] = [];

if (isPassenger(user.role)) {
  childrenItem.push({
    id: autoIncrement(childrenItem) + menuItems.length + 1,
    label: "Passenger Management",
    link: "/home/passenger",
    icon: faUser,
    children: [],
  });
}
if (isAirline(user.role)) {
  childrenItem.push({
    id: autoIncrement(childrenItem) + menuItems.length + 1,
    label: "Airline Management",
    link: "/home/airline",
    icon: faPlaneDeparture,
    children: [],
  });
  // ticket management
  childrenItem.push({
    id: autoIncrement(childrenItem) + menuItems.length + 1,
    label: "Ticket Management",
    link: "/home/ticket",
    icon: faTicket,
    children: [],
  });
}
if (isMerchant(user.role)) {
  childrenItem.push({
    id: autoIncrement(childrenItem) + menuItems.length + 1,
    label: "Merchant Management",
    link: "/home/merchant",
    icon: faBuilding,
    children: [],
  });
}
if (isStaff(user.role)) {
  childrenItem.push({
    id: autoIncrement(childrenItem) + menuItems.length + 1,
    label: "Staff Management",
    link: "/home/staff",
    icon: faUserTie,
    children: [],
  });
}
if (childrenItem.length > 0) {
  menuItems.push({
    id: autoIncrement(menuItems),
    label: "Management",
    link: "",
    icon: faGear,
    children: childrenItem,
  });
}

const headerlessHeight = ref(0);

onMounted(async () => {
  headerlessHeight.value = getHeightWithoutHeader();
  window.addEventListener("resize", () => {
    headerlessHeight.value = getHeightWithoutHeader();
  });
});
</script>

<template>
  <HeaderComp />

  <div
    :style="'height:' + headerlessHeight + 'px'"
    class="pt-[56px] flex flex-col w-full"
  >
    <aside
      id="default-sidebar"
      aria-label="Sidenav"
      class="fixed top-[56px] left-0 z-40 w-72 shadow-lg h-screen transition-transform -translate-x-full sm:translate-x-0"
    >
      <div
        class="overflow-y-auto py-5 px-3 h-full bg-white border-r border-gray-200 dark:bg-gray-800 dark:border-gray-700"
      >
        <ul class="space-y-2">
          <li v-for="item in menuItems" :key="item.id">
            <template v-if="!item.children.length">
              <router-link
                :to="item.link"
                class="flex items-center p-2 font-normal hover:font-bold text-gray-900 rounded-lg dark:text-white hover:bg-pink-100 dark:hover:bg-pink-700 group"
              >
                <font-awesome-icon
                  :icon="item.icon"
                  class="w-6 h-6 text-gray-400 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
                />
                <span class="ml-3 font-normal hover:font-bold">{{
                  item.label
                }}</span>
              </router-link>
            </template>

            <template v-else>
              <li
                class="flex items-center p-2 pb-4 font-normal text-gray-900 rounded-lg dark:text-white group"
              >
                <font-awesome-icon
                  :icon="item.icon"
                  class="w-6 h-6 text-gray-400 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
                />
                <span class="ml-3">{{ item.label }}</span>
              </li>

              <ul class="space-y-2 pl-[1em]">
                <li
                  v-for="child in item.children"
                  :key="child.id"
                  class="flex items-center p-2 font-normal text-gray-900 rounded-lg dark:text-white group"
                >
                  <font-awesome-icon
                    :icon="child.icon"
                    class="w-4 h-4 text-gray-400 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
                  />
                  <router-link :to="child.link">
                    <span class="ml-3 font-normal hover:font-bold">{{
                      child.label
                    }}</span>
                  </router-link>
                </li>
              </ul>
            </template>
          </li>
        </ul>
      </div>
    </aside>
    <div v-if="isAtHomePath" class="ml-72">
      <div
        class="max-w-screen-md mx-auto w-full mt-[18%] flex justify-center place-content-center text-center"
      >
        <div class="flex flex-col justify-center space-y-8 items-center">
          <h3 class="text-3xl font-semibold">
            Welcome! <span class="px-4">{{ user.username }} </span>
          </h3>
          <p class="text-xl">You currently logged in as</p>
          <div class="space-x-4">
            <span
              v-for="role in getUserRoleList(user.role)"
              :key="role"
              class="p-2 w-fit bg-pink-50 rounded-xl ring-0 ring-pink-200 shadow-md font-bold text-pink-800"
            >
              {{ role }}
            </span>
          </div>
        </div>
      </div>
    </div>
    <RouterView v-else />
  </div>
</template>
