<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { TableV2FixedDir } from "element-plus";
import { buyGoodsAPI, getGoodsListAPI } from "@/apis/goods";
import { getGoodsResponse } from "@/types/goods";
import ResizableTableComp from "@/components/ResizableTableComp.vue";

const columns = [
  {
    key: "ids",
    title: "ID",
    dataKey: "ids",
    width: 30,
    align: "center",
  },
  {
    key: "name",
    title: "Name",
    dataKey: "name",
    width: 200,
    align: "center",
  },
  {
    key: "description",
    title: "Description",
    dataKey: "description",
    width: 400,
    align: "center",
  },
  {
    key: "price",
    title: "Price",
    dataKey: "price",
    width: 100,
    align: "center",
  },
  {
    key: "stock",
    title: "Stock",
    dataKey: "stock",
    width: 100,
    align: "center",
  },
  {
    key: "category",
    title: "Category",
    dataKey: "category",
    width: 200,
    align: "center",
  },
  {
    key: "operations",
    title: "Operations",
    cellRenderer: ({ rowData }: any) => (
      <div
        class={
          "bg-pink-500 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-pink-600"
        }
        id={rowData.ids}
        onClick={() => buy(rowData.ids)}
      >
        Buy
      </div>
    ),
    width: 100,
    align: "center",
    fixed: TableV2FixedDir.RIGHT,
  },
];

const data: Ref<getGoodsResponse[]> = ref<getGoodsResponse[]>([]);
const page = ref(1);
const allCount = ref(0);

const getData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getGoodsListAPI(pagination);
  if (!res || res.length === 0) return;
  data.value = res;
};

const buy = async (id: number) => {
  await buyGoodsAPI(id);
  await getData(page.value);
};

onMounted(async () => {
  allCount.value = await getTypesCountStatistic("goods");

  await getData(page.value);
});
const headerlessHeight = ref(0);
onMounted(async () => {
  headerlessHeight.value = getHeightWithoutHeader();
  window.addEventListener("resize", () => {
    headerlessHeight.value = getHeightWithoutHeader();
  });
});
const paginationChange = (v: number) => {
  getData(v);
  page.value = v;
};
</script>

<template>
  <div :style="'height:' + headerlessHeight + 'px'" class="ml-0 lg:ml-72 p-8">
    <div
      class="mx-auto p-8 max-w-screen-xl w-full h-full shadow-lg rounded-lg flex flex-col space-y-4"
    >
      <h1 class="text-2xl font-bold">Goods Details</h1>
      <div class="flex-grow h-full w-full">
        <ResizableTableComp
          :columns="columns"
          :data="data"
          :total="allCount"
          @paginationChange="paginationChange"
        />
      </div>
    </div>
  </div>
</template>
