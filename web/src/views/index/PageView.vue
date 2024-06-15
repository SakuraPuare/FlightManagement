<script lang="ts" setup>
import HeaderComp from "@/components/HeaderComp.vue";
import { useUserStore } from "@/stores/user";
import router from "@/utils/router";
import { onMounted } from "vue";

const user = useUserStore();

onMounted(() => {
  if (!user.isLogin()) {
    router.push("/public");
  }
});
</script>

<template>
  <HeaderComp />
  <router-view />
</template>

<style scoped></style>
