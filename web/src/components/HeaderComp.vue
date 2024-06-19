<script lang="ts" setup>
import { TITLE } from "@/config";
import { useUserStore } from "@/stores/user.ts";
import { ref, watch } from "vue";
import Avatar from "vue-boring-avatars";
import router from "@/utils/router";
import { autoIncrement } from "@/utils/utils";
import { getUserRoleList } from "@/utils/role";

const user = useUserStore();
const isLogin = ref(user.isLogin() && user.userId !== 0);
const isPublic = ref(router.currentRoute.value.meta.title === "Public");

watch(
  () => router.currentRoute.value.meta.title,
  () => {
    if (router.currentRoute.value.meta.title === "Public") {
      isPublic.value = true;
    } else {
      isPublic.value = false;
    }
  },
);

const logout = () => {
  user.logout();
  console.log("router to /");
  router.push("/");
};

const menuItems = [
  { id: 1, label: "Home", link: "/home" },
  { id: 2, label: "Ticket", link: "/home/passenger/ticket" },
  { id: 3, label: "Order", link: "/home/passenger/order" },
  { id: 4, label: "Goods", link: "/home/passenger/goods" },
  { id: 5, label: "Luggage", link: "/home/passenger/luggage" },
];

menuItems.push({
  id: autoIncrement(menuItems),
  label: "About",
  link: "/about",
});
</script>

<template>
  <header id="header">
    <nav
      class="z-50 fixed w-full bg-pink-400 border-gray-200 px-4 lg:px-6 py-2.5 h-14 dark:bg-gray-800"
    >
      <div class="flex lg:space-x-16 mx-auto max-w-screen-xl">
        <div
          class="flex items-center text-xl font-semibold whitespace-nowrap dark:text-white"
          to="/"
        >
          <button class="pr-4" @click="$emit('clickOnLogo')">
            <img alt="{{ TITLE }} Logo" class="h-9 w-9" src="/logo.svg" />
          </button>

          <router-link to="/">
            <span
              class="self-center text-xl font-semibold whitespace-nowrap dark:text-white hidden lg:block"
              >{{ TITLE }}</span
            >
          </router-link>
        </div>

        <div class="flex-grow lg:hidden"></div>
        <div
          class="flex-grow hidden justify-between items-center w-full lg:flex lg:w-auto"
        >
          <ul
            class="flex flex-col mt-4 font-medium lg:flex-row lg:space-x-4 lg:mt-0"
          >
            <li v-for="item in menuItems" :key="item.id">
              <router-link
                :to="item.link"
                class="block font-semibold py-2 pr-4 pl-3 text-gray-800 border-b border-gray-100 hover:bg-gray-50 lg:hover:bg-transparent lg:border-0 lg:hover:text-pink-700 lg:p-0 dark:text-gray-400 lg:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white lg:dark:hover:bg-transparent dark:border-gray-700"
              >
                {{ item.label }}
              </router-link>
            </li>
          </ul>
        </div>

        <div v-if="isLogin && !isPublic" class="w-128">
          <!-- select role -->
          <el-select
            v-model="user.currentRole"
            placeholder="Select role"
            style="width: 120px"
          >
            <el-option
              v-for="role in getUserRoleList(user.role)"
              :key="role"
              :label="role"
              :value="role"
            ></el-option>
          </el-select>
        </div>

        <div class="flex my-auto">
          <template v-if="!isLogin || user.userId === 0">
            <router-link to="/login">
              <span
                class="text-gray-800 dark:text-white hover:bg-gray-50 focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:hover:bg-gray-700 focus:outline-none dark:focus:ring-gray-800"
              >
                Log in
              </span>
            </router-link>
            <router-link to="/register">
              <span
                class="text-white bg-pink-700 hover:bg-pink-800 focus:ring-4 focus:ring-pink-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:bg-pink-600 dark:hover:bg-pink-700 focus:outline-none dark:focus:ring-pink-800"
                >Get started</span
              >
            </router-link>
          </template>
          <template v-else>
            <a :href="`/#/users/${user.userId}`">
              <Avatar
                :name="user.username"
                :size="36"
                class="mx-5"
                variant="beam"
              />
            </a>
            <span
              class="text-white bg-pink-700 hover:bg-pink-800 select-none focus:ring-4 focus:ring-pink-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:bg-pink-600 dark:hover:bg-pink-700 focus:outline-none dark:focus:ring-pink-800"
              @click="logout"
              >Logout</span
            >
          </template>
        </div>
      </div>
    </nav>
  </header>
</template>

<style scoped></style>
