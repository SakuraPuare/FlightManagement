<template>
  <router-link to="/">
    <span
      :class="
        'flex items-center mb-6 font-semibold text-gray-900 dark:text-white my-auto ' +
        fontSize
      "
    >
      <img :class="'mr-4 ' + logoSize" alt="logo" src="/logo.svg" />
      {{ TITLE }}
    </span>
  </router-link>
</template>

<script lang="ts" setup>
import { TITLE } from "@/config";

defineProps({
  fontSize: {
    type: String,
    default: "text-2xl",
  },
  logoSize: {
    type: String,
    default: "w-10 h-10",
  },
});
</script>
