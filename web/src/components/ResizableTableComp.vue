<!--
  - Copyright (c) 2024. All right reserved by SakuraPuare.
  -->

<template>
  <div class="flex flex-col h-full w-full">
    <el-auto-resizer class="flex-grow">
      <template #default="{ height, width }">
        <el-table-v2
          :columns="columns"
          :data="data"
          :estimated-row-height="50"
          :fixed="true"
          :height="height"
          :width="width"
        >
          <template #footer>
            <div
              class="flex items-center pb-4"
              style="justify-content: center; height: 100%"
            ></div>
          </template>
        </el-table-v2>
      </template>
    </el-auto-resizer>
    <el-pagination
      :total="total"
      background
      class="mx-auto"
      layout="prev, pager, next"
      @current-change="$emit('paginationChange', $event)"
    />
  </div>
</template>

<script lang="ts" setup>
defineProps({
  columns: {
    type: Array,
    required: true,
  },
  data: {
    type: Array,
    required: true,
  },
  total: {
    type: Number,
    required: true,
  },
});
</script>
