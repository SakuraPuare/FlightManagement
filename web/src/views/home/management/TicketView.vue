<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import {
  createTicketAPI,
  deleteTicketAPI,
  getTicketsListAPI,
  updateTicketAPI,
} from "@/apis/tickets";
import { getTicketResponse, updateTicketRequest } from "@/types/ticket";
import { Pagination } from "@/types/types";
import { DateRender } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { TableV2FixedDir } from "element-plus";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { getFlightResponse } from "@/types/flight";
import { getFlightsListAPI } from "@/apis/flights";

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
      <div class={"flex flex-row  space-x-2"}>
        <button
          class={
            "bg-yellow-500 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-yellow-600"
          }
          id={rowData.ids}
          onClick={() => {
            modifyDialog.value = true;
            sync(rowData.ids);
          }}
        >
          Modify
        </button>{" "}
        <button
          class={
            "bg-pink-500 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-pink-600"
          }
          id={rowData.ids}
          onClick={() => {
            deleteDialog.value = true;
            sync(rowData.ids);
          }}
        >
          Delete
        </button>
      </div>
    ),
    width: 200,
    align: "center",
    fixed: TableV2FixedDir.RIGHT,
  },
];

const createDialog = ref(false);
const deleteDialog = ref(false);
const modifyDialog = ref(false);

const ticketData = ref({
  id: 0,
  flightId: 0,
  seatClass: "",
  quota: 0,
  price: "",
});

const add = async () => {
  // sanity check
  if (
    ticketData.value.flightId === 0 ||
    ticketData.value.seatClass === "" ||
    ticketData.value.price === ""
  ) {
    return;
  }

  const params: updateTicketRequest = { ...ticketData.value };
  await createTicketAPI(params);

  createDialog.value = false;
  clean();
};

const del = async () => {
  await deleteTicketAPI(ticketData.value.id);
  deleteDialog.value = false;
  clean();
};

const modify = async () => {
  const params: updateTicketRequest = { ...ticketData.value };
  await updateTicketAPI(ticketData.value.id, params);
  modifyDialog.value = false;
  clean();
};

const sync = (id: number) => {
  const row = tableData.value.find((item) => item.ids === id);
  ticketData.value = { id: row.ids, ...row };
};

const clean = () => {
  ticketData.value = {
    id: 0,
    flightId: 0,
    seatClass: "",
    quota: 0,
    price: "",
  };
  getTableData(tablePage.value);
};

const tableData: Ref<getTicketResponse[]> = ref<getTicketResponse[]>([]);
const tablePage = ref(1);
const tableCount = ref(0);
const flightList = ref<getFlightResponse[]>([]);
const flightPage = ref(1);
const flightCount = ref(0);

const getTableData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getTicketsListAPI(pagination);
  if (!res || res.length === 0) return;
  tableData.value = res;
};

const getFlightData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 100,
  };
  const res = await getFlightsListAPI(pagination);
  if (!res || res.length === 0) return;
  flightList.value.push(...res);
};

onMounted(async () => {
  tableCount.value = await getTypesCountStatistic("ticket");
  flightCount.value = await getTypesCountStatistic("flight");
  await getTableData(tablePage.value);
  while ((flightPage.value - 1) * 100 < flightCount.value) {
    await getFlightData(flightPage.value++);
  }
});

const headerlessHeight = ref(0);
onMounted(async () => {
  headerlessHeight.value = getHeightWithoutHeader();
  window.addEventListener("resize", () => {
    headerlessHeight.value = getHeightWithoutHeader();
  });
});

const tablePaginationChange = (v: number) => {
  getTableData(v);
  tablePage.value = v;
};
</script>

<template>
  <div :style="'height:' + headerlessHeight + 'px'" class="ml-72 p-8">
    <div
      class="mx-auto p-8 max-w-screen-xl w-full h-full shadow-lg rounded-lg flex flex-col space-y-4"
    >
      <div class="flex">
        <h1 class="text-2xl font-bold">Ticket Management</h1>
        <div class="flex-grow" />
        <button
          class="flex place-content-center text-white justify-items-center items-center bg-pink-500 text-center rounded-lg shadow-lg space-x-2 px-2 py-2 my-atuo cursor-pointer hover:font-bold hover:bg-pink-600"
          @click="createDialog = true"
        >
          <font-awesome-icon class="text-white h-4 w-4" :icon="faPlus" />
          <div class="font-bold">Add</div>
        </button>
      </div>

      <div class="flex-grow h-full w-full">
        <ResizableTableComp
          :columns="columns"
          :data="tableData"
          :total="tableCount"
          @paginationChange="tablePaginationChange"
        />
      </div>
    </div>
  </div>

  <el-dialog
    v-model="createDialog"
    title="Create a Ticket"
    width="500"
    align-center
  >
    <el-form :model="ticketData" label-width="120px">
      <el-form-item label="Flight ID">
        <el-select v-model="ticketData.flightId" placeholder="Select Flight">
          <el-option
            v-for="flight in flightList"
            :key="flight.ids"
            :label="flight.flightNumber"
            :value="flight.ids"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Seat Class">
        <el-input
          v-model="ticketData.seatClass"
          placeholder="Enter Seat Class"
        ></el-input>
      </el-form-item>
      <el-form-item label="Quota">
        <el-input
          v-model="ticketData.quota"
          placeholder="Enter Quota"
        ></el-input>
      </el-form-item>
      <el-form-item label="Price">
        <el-input
          v-model="ticketData.price"
          placeholder="Enter Price"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="space-x-4">
        <button
          @click="createDialog = false"
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-lg"
        >
          Cancel
        </button>
        <button
          @click="add"
          class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded-lg shadow-lg"
        >
          Confirm
        </button>
      </div>
    </template>
  </el-dialog>

  <el-dialog
    v-model="modifyDialog"
    title="Modify Ticket"
    width="500"
    align-center
  >
    <el-form :model="ticketData" label-width="120px">
      <el-form-item label="Flight ID">
        <el-select v-model="ticketData.flightId" placeholder="Select Flight">
          <el-option
            v-for="flight in flightList"
            :key="flight.ids"
            :label="flight.flightNumber"
            :value="flight.ids"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Seat Class">
        <el-input
          v-model="ticketData.seatClass"
          placeholder="Enter Seat Class"
        ></el-input>
      </el-form-item>
      <el-form-item label="Quota">
        <el-input
          v-model="ticketData.quota"
          placeholder="Enter Quota"
        ></el-input>
      </el-form-item>
      <el-form-item label="Price">
        <el-input
          v-model="ticketData.price"
          placeholder="Enter Price"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="space-x-4">
        <button
          @click="modifyDialog = false"
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-lg"
        >
          Cancel
        </button>
        <button
          @click="modify"
          class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded-lg shadow-lg"
        >
          Confirm
        </button>
      </div>
    </template>
  </el-dialog>

  <el-dialog
    v-model="deleteDialog"
    title="Delete Ticket"
    width="500"
    align-center
  >
    <p class="text-lg text-red-500 font-semibold">
      Are you sure you want to delete this ticket?
    </p>
    <p class="text-lg text-red-500 font-semibold">
      All data that depends on this ticket will be deleted.
    </p>
    <template #footer>
      <div class="space-x-4">
        <button
          @click="deleteDialog = false"
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-lg"
        >
          Cancel
        </button>
        <button
          @click="del"
          class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-lg shadow-lg"
        >
          Delete
        </button>
      </div>
    </template>
  </el-dialog>
</template>
