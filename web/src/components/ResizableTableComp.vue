<template>
  <el-auto-resizer>
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
          >
            <el-pagination
              :total="total"
              background
              layout="prev, pager, next"
              @current-change="paginationChangeHandler"
            />
          </div>
        </template>
      </el-table-v2>
    </template>
  </el-auto-resizer>
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

const emit = defineEmits(["paginationChange"]);

const paginationChangeHandler = (page: number) => {
  emit("paginationChange", page);
};
</script>
