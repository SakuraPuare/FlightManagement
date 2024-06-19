<!--
  - Copyright (c) 2024. All right reserved by SakuraPuare.
  -->

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import { loginAPI } from "@/apis/auth";
import { useUserStore } from "@/stores/user";
import { LoginParams } from "@/types/auth/login";
import router from "@/utils/router";
import TitleComp from "@/components/TitleComp.vue";

const user = useUserStore();

const username = ref("");
const password = ref("");

const login = async (e: Event) => {
  e.preventDefault();
  const params: LoginParams = {
    username: username.value,
    password: password.value,
  };
  const res = await loginAPI(params);
  user.setToken(res);
  user.setUser(res);
  console.log("router to /home");
  await router.push("/home");
};

onMounted(() => {
  if (user.isLogin()) {
    console.log("router to /home");
    router.push("/home");
  }
});
</script>

<template>
  <section class="bg-gray-50 dark:bg-gray-900">
    <div
      class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0"
    >
      <TitleComp />
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1
            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"
          >
            Sign in to your account
          </h1>
          <form class="space-y-4 md:space-y-6" @submit="login">
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="username"
                >Username</label
              >
              <input
                v-model="username"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="username"
                placeholder="Enter your username"
                required
                type="text"
              />
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="password"
                >Password</label
              >
              <input
                v-model="password"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="password"
                placeholder="••••••••"
                required
                type="password"
              />
            </div>
            <div class="flex items-center justify-between">
              <div class="flex items-start">
                <div class="flex items-center h-5">
                  <input
                    id="remember"
                    class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-pink-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-pink-600 dark:ring-offset-gray-800"
                    type="checkbox"
                  />
                </div>
                <div class="ml-3 text-sm">
                  <label class="text-gray-500 dark:text-gray-300" for="remember"
                    >Remember me</label
                  >
                </div>
              </div>
              <!-- <a class="text-sm font-medium text-pink-600 hover:underline dark:text-pink-500" href="#">Forgot
                password?</a> -->
            </div>
            <button
              class="w-full text-white bg-pink-600 hover:bg-pink-700 focus:ring-4 focus:outline-none focus:ring-pink-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-pink-600 dark:hover:bg-pink-700 dark:focus:ring-pink-800"
              type="submit"
            >
              Sign in
            </button>
            <p class="text-sm font-light text-gray-500 dark:text-gray-400">
              Don't have an account yet?
              <router-link to="/register">
                <span
                  class="font-medium text-pink-600 hover:underline dark:text-pink-500"
                  >Sign up</span
                >
              </router-link>
            </p>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
