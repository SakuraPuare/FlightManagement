<script lang="tsx" setup>
import { createOrderAPI } from "@/apis/orders";
import { getTypesCountStatistic } from "@/apis/statistic";
import { getTicketsListAPI } from "@/apis/tickets";
import { getTicketResponse } from "@/types/ticket";
import { Pagination } from "@/types/types";
import { DateRender } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { TableV2FixedDir } from "element-plus";
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
    key: "airlineName",
    title: "Airline Name",
    dataKey: "airlineName",
    width: 150,
    align: "center",
  },
  {
    key: "airlineCountry",
    title: "Airline Country",
    dataKey: "airlineCountry",
    width: 100,
    align: "center",
  },
  {
    key: "flightNumber",
    title: "Flight Number",
    dataKey: "flightNumber",
    width: 80,
    align: "center",
  },
  {
    key: "departureCity",
    title: "Departure City",
    dataKey: "departureCity",
    width: 80,
    align: "center",
  },
  {
    key: "arrivalCity",
    title: "Arrival City",
    dataKey: "arrivalCity",
    width: 80,
    align: "center",
  },
  {
    key: "dateOfDeparture",
    title: "Date Of Departure",
    dataKey: "dateOfDeparture",
    width: 120,
    align: "center",
    cellRenderer: ({
      cellData: dateOfDeparture,
    }: {
      cellData: string;
    }): string => DateRender(dateOfDeparture),
  },
  {
    key: "estimatedTravelTime",
    title: "Estimated Travel Time",
    dataKey: "estimatedTravelTime",
    width: 100,
    align: "center",
    cellRenderer: ({ cellData: estimatedTravelTime }: { cellData: number }) => {
      // in minutes
      return (
        Math.floor(estimatedTravelTime / 60) +
        "h " +
        (estimatedTravelTime % 60) +
        "m"
      );
    },
  },
  {
    key: "seatClass",
    title: "Seat Class",
    dataKey: "seatClass",
    width: 100,
    align: "center",
  },
  {
    key: "capacity",
    title: "Capacity",
    dataKey: "capacity",
    width: 80,
    align: "center",
  },
  {
    key: "quota",
    title: "Quota",
    dataKey: "quota",
    width: 80,
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

const data: Ref<getTicketResponse[]> = ref<getTicketResponse[]>([]);
const page = ref(1);
const allCount = ref(0);

const getData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getTicketsListAPI(pagination);
  if (!res || res.length === 0) return;
  data.value = res;
};

const buy = async (id: number) => {
  await createOrderAPI(id);
  await getData(page.value);
};

onMounted(async () => {
  allCount.value = await getTypesCountStatistic("ticket");

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
      <h1 class="text-2xl font-bold">Ticket Details</h1>
      <!-- <div class="flex flex-wrap">
        <div v-for="_ in 20" class="h-10 w-10 m-8 bg-pink-500">{{ _ }}</div>
      </div> -->
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
