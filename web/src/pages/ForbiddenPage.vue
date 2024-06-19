<!--
  - Copyright (c) 2024. All right reserved by SakuraPuare.
  -->

<template>
  <section class="bg-gray-200 dark:bg-gray-900">
    <div class="py-8 px-4 mx-auto max-w-screen-xl h-screen lg:py-16 lg:px-6">
      <div class="mx-auto max-w-screen-sm text-center">
        <h1
          class="mb-4 text-7xl tracking-tight font-extrabold lg:text-9xl text-pink-600 dark:text-pink-500"
        >
          403
        </h1>
        <p
          class="mb-4 text-3xl tracking-tight font-bold text-gray-900 md:text-4xl dark:text-white"
        >
          Forbidden
        </p>
        <p class="mb-4 text-lg font-light text-gray-500 dark:text-gray-400">
          Sorry, you don't have permission to access this page.
        </p>
        <router-link
          class="inline-flex text-white bg-pink-600 hover:bg-pink-800 focus:ring-4 focus:outline-none focus:ring-pink-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:focus:ring-pink-900 my-4"
          to="/login"
        >
          Back to Login
        </router-link>

        <p class="mt-8 text-sm font-light text-gray-500 dark:text-gray-400">
          You will be redirected to the login page in {{ countdown }} seconds.
        </p>
      </div>
    </div>
  </section>
</template>

<script lang="ts" setup>
import { onMounted, onUnmounted, ref } from "vue";
import router from "@/utils/router";

const countdown = ref(3);

const startCountdown = () => {
  const timer = setInterval(() => {
    if (countdown.value > 0) {
      countdown.value--;
    } else if (countdown.value === 0) {
      console.log("router to /login");
      router.push("/login");
    }
  }, 1000);

  onUnmounted(() => {
    clearInterval(timer);
  });
};

onMounted(startCountdown);
</script>
