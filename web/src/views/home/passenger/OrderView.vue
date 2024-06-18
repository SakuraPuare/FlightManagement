<script lang="tsx" setup>
import { cancelOrderAPI, getOrdersListAPI, payOrderAPI } from "@/apis/orders";
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { DateRender } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, Ref, ref } from "vue";
import { getOrderResponse } from "@/types/order";
import { TableV2FixedDir } from "element-plus";

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
    key: "price",
    title: "Price",
    dataKey: "price",
    width: 100,
    align: "center",
  },
  {
    key: "status",
    title: "Status",
    dataKey: "status",
    width: 80,
    align: "center",
    cellRenderer: ({ cellData: status }: { cellData: string }) => {
      if (status === "unpaid")
        return <div class="text-red-500 font-bold">Unpaid</div>;
      if (status === "paid")
        return <div class="text-green-500 font-bold">Paid</div>;
      if (status === "canceled")
        return <div class="text-gray-500 font-bold">Canceled</div>;
    },
  },
  {
    key: "createdAt",
    title: "Created At",
    dataKey: "createdAt",
    width: 80,
    align: "center",
    cellRenderer: ({ cellData: createdAt }: { cellData: string }): string =>
      DateRender(createdAt),
  },
  {
    key: "updatedAt",
    title: "Updated At",
    dataKey: "updatedAt",
    width: 80,
    align: "center",
    cellRenderer: ({ cellData: updatedAt }: { cellData: string }): string =>
      DateRender(updatedAt),
  },
  {
    key: "operations",
    title: "Operations",
    cellRenderer: ({ rowData }: any) => (
      <div class={"flex flex-row space-x-2"}>
        <div
          v-show={rowData.status === "unpaid"}
          class={
            "bg-green-600 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-green-800"
          }
          onClick={() => pay(rowData.ids)}
        >
          Pay
        </div>
        <div
          v-show={rowData.status !== "canceled"}
          class={
            "bg-pink-600 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-pink-800"
          }
          onClick={() => cancel(rowData.ids)}
        >
          Cancel
        </div>
      </div>
    ),
    width: 150,
    align: "center",
    fixed: TableV2FixedDir.RIGHT,
  },
];

const data: Ref<getOrderResponse[]> = ref<getOrderResponse[]>([]);
const page = ref(1);
const allCount = ref(0);

const getData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getOrdersListAPI(pagination);
  if (!res || res.length === 0) return;
  data.value = res;
};

const pay = async (id: number) => {
  await payOrderAPI(id);
  getData(page.value);
};

const cancel = async (id: number) => {
  await cancelOrderAPI(id);
  getData(page.value);
};

onMounted(async () => {
  allCount.value = await getTypesCountStatistic("order");

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
      <h1 class="text-2xl font-bold">Order Details</h1>
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
