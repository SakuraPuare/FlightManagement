<script lang="ts" setup>
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
  <router-view />
</template>

<style scoped></style>
