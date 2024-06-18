<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { getMyLuggagesListAPI } from "@/apis/luggages";
import { getLuggageResponse } from "@/types/luggage";
import { DateRender } from "@/utils/date";

const columns = [
  {
    key: "ids",
    title: "ID",
    dataKey: "ids",
    width: 50,
    align: "center",
  },
  {
    key: "weight",
    title: "Weight",
    dataKey: "weight",
    width: 100,
    align: "center",
  },
  {
    key: "staffId",
    title: "Staff ID",
    dataKey: "staffId",
    width: 100,
    align: "center",
  },
  {
    key: "createdAt",
    title: "Created At",
    dataKey: "createdAt",
    width: 150,
    align: "center",
    cellRenderer: ({
      cellData: dateOfDeparture,
    }: {
      cellData: string;
    }): string => DateRender(dateOfDeparture),
  },
  {
    key: "updatedAt",
    title: "Updated At",
    dataKey: "updatedAt",
    width: 150,
    align: "center",
    cellRenderer: ({
      cellData: dateOfDeparture,
    }: {
      cellData: string;
    }): string => DateRender(dateOfDeparture),
  },
];

const data: Ref<getLuggageResponse[]> = ref<getLuggageResponse[]>([]);
const page = ref(1);
const allCount = ref(0);

const getData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getMyLuggagesListAPI(pagination);
  if (!res || res.length === 0) return;
  data.value = res;
};

onMounted(async () => {
  allCount.value = await getTypesCountStatistic("luggage");

  getData(page.value);
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
  <div :style="'height:' + headerlessHeight + 'px'" class="ml-72 p-8">
    <div
      class="mx-auto p-8 max-w-screen-xl w-full h-full shadow-lg rounded-lg flex flex-col space-y-4"
    >
      <h1 class="text-2xl font-bold">Luggage Details</h1>
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
