<script lang="ts" setup>
import { TITLE } from "@/config";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { useUserStore } from "@/stores/user.ts";
import { ref } from "vue";
import Avatar from "vue-boring-avatars";

const menuItems = [
  { id: 1, label: "Home", link: "/" },
  { id: 2, label: "Public", link: "/welcome" },
  // { id: 3, label: "Marketplace", link: "/" },
  // { id: 4, label: "Features", link: "/" },
  // { id: 5, label: "Team", link: "/" },
  // { id: 6, label: "Contact", link: "/" },
];

const user = useUserStore();
const isLogin = ref(user.isLogin());
</script>

<template>
  <header id="header">
    <nav
      class="bg-pink-400 border-gray-200 px-4 lg:px-6 py-2.5 h-14 dark:bg-gray-800"
    >
      <div
        class="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl"
      >
        <router-link
          class="flex items-center text-xl font-semibold whitespace-nowrap dark:text-white"
          to="/"
        >
          <img alt="{{ TITLE }} Logo" class="mr-3 h-6 sm:h-9" src="/logo.svg" />
          <span
            class="self-center text-xl font-semibold whitespace-nowrap dark:text-white hidden lg:block"
            >{{ TITLE }}</span
          >
        </router-link>
        <div class="flex items-center lg:order-2">
          <template v-if="!isLogin">
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
            <router-link :to="`/users/${user.userId}`">
              <Avatar
                :name="user.username"
                :size="36"
                class="mx-5"
                variant="beam"
              />
            </router-link>
            <span
              class="text-white bg-pink-700 hover:bg-pink-800 select-none focus:ring-4 focus:ring-pink-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:bg-pink-600 dark:hover:bg-pink-700 focus:outline-none dark:focus:ring-pink-800"
              @click="
                user.logout();
                isLogin = false;
              "
              >Log out</span
            >
          </template>

          <button
            class="inline-flex items-center p-2 ml-1 text-sm text-gray-500 rounded-lg lg:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
            type="button"
          >
            <span class="sr-only">Open main menu</span>
            <font-awesome-icon :icon="faBars" class="w-6 h-6" />
          </button>
        </div>
        <div
          id="mobile-menu-2"
          class="hidden justify-between items-center w-full lg:flex lg:w-auto lg:order-1"
        >
          <ul
            class="flex flex-col mt-4 font-medium lg:flex-row lg:space-x-8 lg:mt-0"
          >
            <li v-for="item in menuItems" :key="item.id">
              <router-link
                :to="item.link"
                class="block py-2 pr-4 pl-3 text-gray-800 border-b border-gray-100 hover:bg-gray-50 lg:hover:bg-transparent lg:border-0 lg:hover:text-pink-700 lg:p-0 dark:text-gray-400 lg:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white lg:dark:hover:bg-transparent dark:border-gray-700"
              >
                {{ item.label }}
              </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<style scoped></style>
