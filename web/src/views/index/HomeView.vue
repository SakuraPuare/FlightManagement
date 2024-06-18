<script lang="ts" setup>
import { useUserStore } from "@/stores/user";
import { getUserRoleList } from "@/utils/role";

import {
  faClipboard,
  faGear,
  faHome,
  faPlaneDeparture,
  faStore,
  faSuitcase,
  faTicket,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { autoIncrement } from "@/utils/utils";
import { onMounted, Ref, ref, watch } from "vue";
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

// watch user.currRole change
watch(
  () => user.currentRole,
  () => {
    update();
    // router to home
    if (router.currentRoute.value.path !== "/home") {
      router.push("/home");
    }
  },
);

const menuItems: Ref<MenuItem[]> = ref<MenuItem[]>([]);

const update = () => {
  // push default
  menuItems.value = [];
  menuItems.value.push({
    id: autoIncrement(menuItems.value),
    label: "Home",
    link: "/home",
    icon: faHome,
    children: [],
  });
  let managementChildren: MenuItem[] = [];
  if (user.currentRole === "Passenger") {
    menuItems.value.push({
      id: autoIncrement(menuItems.value),
      label: "Ticket",
      link: "/home/passenger/ticket",
      icon: faTicket,
      children: [],
    });
    menuItems.value.push({
      id: autoIncrement(menuItems.value),
      label: "Order",
      link: "/home/passenger/order",
      icon: faPlaneDeparture,
      children: [],
    });
    menuItems.value.push({
      id: autoIncrement(menuItems.value),
      label: "Goods",
      link: "/home/passenger/goods",
      icon: faStore,
      children: [],
    });
    menuItems.value.push({
      id: autoIncrement(menuItems.value),
      label: "Luggage",
      link: "/home/passenger/luggage",
      icon: faSuitcase,
      children: [],
    });
  } else if (user.currentRole === "Merchant") {
    managementChildren.push({
      id: autoIncrement(menuItems.value),
      label: "Goods",
      link: "/home/merchant/goods",
      icon: faStore,
      children: [],
    });
  } else if (user.currentRole === "Airline") {
    managementChildren.push({
      id: autoIncrement(menuItems.value),
      label: "Order",
      link: "/home/airline/order",
      icon: faClipboard,
      children: [],
    });
    managementChildren.push({
      id: autoIncrement(menuItems.value),
      label: "Ticket",
      link: "/home/airline/ticket",
      icon: faTicket,
      children: [],
    });
    // flight
    managementChildren.push({
      id: autoIncrement(menuItems.value),
      label: "Flight",
      link: "/home/airline/flight",
      icon: faPlaneDeparture,
      children: [],
    });
  } else if (user.currentRole === "Staff") {
  }
  if (managementChildren.length > 0) {
    menuItems.value.push({
      id: autoIncrement(menuItems.value),
      label: "Management",
      link: "",
      icon: faGear,
      children: managementChildren,
    });
  }
};

// let childrenItem: MenuItem[] = [];

// if (isAirline(user.role)) {
//   childrenItem.push(
//     {
//       id: autoIncrement(childrenItem) + menuItems.length + 1,
//       label: "Order (Airline)",
//       link: "/home/order",
//       icon: faPlaneDeparture,
//       children: [],
//     },
//     {
//       id: autoIncrement(childrenItem) + menuItems.length + 1,
//       label: "Ticket (Airline)",
//       link: "/home/ticket",
//       icon: faTicket,
//       children: [],
//     },
//   );
// }

// if (isMerchant(user.role)) {
//   childrenItem.push({
//     id: autoIncrement(childrenItem) + menuItems.length + 1,
//     label: "Goods (Merchant)",
//     link: "/home/goods",
//     icon: faBuilding,
//     children: [],
//   });
// }

// if (isStaff(user.role)) {
//   childrenItem.push(
//     {
//       id: autoIncrement(childrenItem) + menuItems.length + 1,
//       label: "User (Staff)",
//       link: "/home/user",
//       icon: faUser,
//       children: [],
//     },
//     {
//       id: autoIncrement(childrenItem) + menuItems.length + 1,
//       label: "Merchant (Staff)",
//       link: "/home/merchant",
//       icon: faUserTie,
//       children: [],
//     },
//     {
//       id: autoIncrement(childrenItem) + menuItems.length + 1,
//       label: "Airline (Staff)",
//       link: "/home/airline",
//       icon: faPlaneDeparture,
//       children: [],
//     },
//     {
//       id: autoIncrement(childrenItem) + menuItems.length + 1,
//       label: "Luggage (Staff)",
//       link: "/home/luggage",
//       icon: faSuitcase,
//       children: [],
//     },
//   );
// }

// if (childrenItem.length > 0) {
//   menuItems.push({
//     id: autoIncrement(menuItems),
//     label: "Management",
//     link: "",
//     icon: faGear,
//     children: childrenItem,
//   });
// }

const headerlessHeight = ref(0);

onMounted(async () => {
  headerlessHeight.value = getHeightWithoutHeader();
  window.addEventListener("resize", () => {
    headerlessHeight.value = getHeightWithoutHeader();
  });
  update();
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
      class="select-none fixed top-[56px] left-0 z-40 w-72 shadow-lg h-screen transition-transform -translate-x-full sm:translate-x-0"
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
    <div v-if="isAtHomePath" class="ml-72 select-none">
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
