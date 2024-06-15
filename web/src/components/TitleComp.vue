<template>
  <router-link to="/">
    <span class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
      <img alt="logo" class="w-10 h-10 mr-4" src="/logo.svg" />
      {{ TITLE }}
    </span>
  </router-link>
</template>

<script lang="ts" setup>
import { TITLE } from "@/config";
</script>
