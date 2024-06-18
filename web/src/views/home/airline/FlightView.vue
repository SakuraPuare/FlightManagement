<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { DateRender } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { TableV2FixedDir } from "element-plus";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import {
  createFlightRequest,
  getFlightResponse,
  updateFlightRequest,
} from "@/types/flight";
import { getAirlinesListAPI } from "@/apis/user";
import { Airline } from "@/types/users";
import {
  createFlightAPI,
  deleteFlightAPI,
  getFlightsListAPI,
  updateFlightAPI,
} from "@/apis/flights";

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
    key: "airlineCode",
    title: "Airline Code",
    dataKey: "airlineCode",
    width: 80,
    align: "center",
  },
  {
    key: "airlineDescription",
    title: "Airline Description",
    dataKey: "airlineDescription",
    width: 200,
    align: "center",
  },
  {
    key: "flightNumber",
    title: "Flight Number",
    dataKey: "flightNumber",
    width: 80,
    align: "center",
  },
  // capacity
  {
    key: "capacity",
    title: "Capacity",
    dataKey: "capacity",
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
    width: 150,
    align: "center",
    fixed: TableV2FixedDir.RIGHT,
  },
];

const createDialog = ref(false);
const deleteDialog = ref(false);
const modifyDialog = ref(false);

const flightData = ref({
  id: 0,
  airlineId: 0,
  flightNumber: "",
  capacity: 0,
  departureCity: "",
  arrivalCity: "",
  dateOfDeparture: "",
  estimatedTravelTime: 0,
});

const add = async () => {
  // sanity check
  console.log(flightData.value);
  if (
    flightData.value.airlineId === 0 ||
    flightData.value.flightNumber === "" ||
    flightData.value.capacity === 0 ||
    flightData.value.departureCity === "" ||
    flightData.value.arrivalCity === "" ||
    flightData.value.dateOfDeparture === "" ||
    flightData.value.estimatedTravelTime === 0
  ) {
    return;
  }

  const params: createFlightRequest = { ...flightData.value };
  await createFlightAPI(params);
  createDialog.value = false;
  clean();
};

const del = async () => {
  await deleteFlightAPI(flightData.value.id);
  deleteDialog.value = false;
  clean();
};

const modify = async () => {
  const params: updateFlightRequest = { ...flightData.value };
  await updateFlightAPI(flightData.value.id, params);
  modifyDialog.value = false;
  clean();
};

const sync = (id: number) => {
  const row = tableData.value.find((item) => item.ids === id);
  if (!row) return;
  console.log(row);
  flightData.value = { id: row.ids, ...row };
};

const clean = () => {
  getTableData(tablePage.value);
};

const tableData: Ref<getFlightResponse[]> = ref<getFlightResponse[]>([]);
const tablePage = ref(1);
const tableCount = ref(0);
const airlineList = ref<Airline[]>([]);
const airlinePage = ref(1);
const airlineCount = ref(0);

const getTableData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getFlightsListAPI(pagination);
  if (!res || res.length === 0) return;
  tableData.value = res;
};

const getAirlineData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 100,
  };
  const res = await getAirlinesListAPI(pagination);
  if (!res || res.length === 0) return;
  airlineList.value.push(...res);
};

onMounted(async () => {
  tableCount.value = await getTypesCountStatistic("flight");
  airlineCount.value = await getTypesCountStatistic("airline");
  await getTableData(tablePage.value);
  while ((airlinePage.value - 1) * 100 < airlineCount.value) {
    await getAirlineData(airlinePage.value++);
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
        <h1 class="text-2xl font-bold">Flight Management</h1>
        <div class="flex-grow" />
        <button
          class="flex place-content-center text-white justify-items-center items-center bg-pink-500 text-center rounded-lg shadow-lg space-x-2 px-2 py-2 my-atuo cursor-pointer hover:font-bold hover:bg-pink-600"
          @click="createDialog = true"
        >
          <font-awesome-icon :icon="faPlus" class="text-white h-4 w-4" />
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
    :draggable="true"
    align-center
    title="Create a Flight"
    width="500"
  >
    <el-form :model="flightData" label-width="200px">
      <el-form-item label="Airline ID">
        <el-select v-model="flightData.airlineId" placeholder="Select Airline">
          <el-option
            v-for="airline in airlineList"
            :key="airline.airlineId"
            :label="airline.airlineName"
            :value="airline.airlineId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Flight Number">
        <el-input
          v-model="flightData.flightNumber"
          placeholder="Enter Flight Number"
        ></el-input>
      </el-form-item>
      <el-form-item label="Capacity">
        <el-input
          v-model="flightData.capacity"
          placeholder="Enter Capacity"
        ></el-input>
      </el-form-item>
      <el-form-item label="Departure City">
        <el-input
          v-model="flightData.departureCity"
          placeholder="Enter Departure City"
        ></el-input>
      </el-form-item>
      <el-form-item label="Arrival City">
        <el-input
          v-model="flightData.arrivalCity"
          placeholder="Enter Arrival City"
        ></el-input>
      </el-form-item>
      <el-form-item label="Date of Departure">
        <el-date-picker
          v-model="flightData.dateOfDeparture"
          placeholder="Select Date and Time"
          type="datetime"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="Estimated Travel Time">
        <el-input
          v-model="flightData.estimatedTravelTime"
          placeholder="Enter Estimated Travel Time"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="space-x-4">
        <button
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-lg"
          @click="createDialog = false"
        >
          Cancel
        </button>
        <button
          class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded-lg shadow-lg"
          @click="add"
        >
          Confirm
        </button>
      </div>
    </template>
  </el-dialog>

  <el-dialog
    v-model="modifyDialog"
    :draggable="true"
    align-center
    title="Modify Flight"
    width="500"
  >
    <el-form :model="flightData" label-width="200px">
      <el-form-item label="Airline ID">
        <el-select v-model="flightData.airlineId" placeholder="Select Airline">
          <el-option
            v-for="airline in airlineList"
            :key="airline.airlineId"
            :label="airline.airlineName"
            :value="airline.airlineId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Flight Number">
        <el-input
          v-model="flightData.flightNumber"
          placeholder="Enter Flight Number"
        ></el-input>
      </el-form-item>
      <el-form-item label="Capacity">
        <el-input
          v-model="flightData.capacity"
          placeholder="Enter Capacity"
        ></el-input>
      </el-form-item>
      <el-form-item label="Departure City">
        <el-input
          v-model="flightData.departureCity"
          placeholder="Enter Departure City"
        ></el-input>
      </el-form-item>
      <el-form-item label="Arrival City">
        <el-input
          v-model="flightData.arrivalCity"
          placeholder="Enter Arrival City"
        ></el-input>
      </el-form-item>
      <el-form-item label="Date of Departure">
        <el-date-picker
          v-model="flightData.dateOfDeparture"
          placeholder="Select Date and Time"
          type="datetime"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="Estimated Travel Time">
        <el-input
          v-model="flightData.estimatedTravelTime"
          placeholder="Enter Estimated Travel Time"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="space-x-4">
        <button
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-lg"
          @click="modifyDialog = false"
        >
          Cancel
        </button>
        <button
          class="bg-pink-500 hover:bg-pink-700 text-white font-bold py-2 px-4 rounded-lg shadow-lg"
          @click="modify"
        >
          Confirm
        </button>
      </div>
    </template>
  </el-dialog>

  <el-dialog
    v-model="deleteDialog"
    :draggable="true"
    align-center
    title="Delete Flight"
    width="500"
  >
    <p class="text-lg text-red-500 font-semibold">
      Are you sure you want to delete this flight?
    </p>
    <p class="text-lg text-red-500 font-semibold">
      All data that depends on this flight will be deleted.
    </p>
    <template #footer>
      <div class="space-x-4">
        <button
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-lg shadow-lg"
          @click="deleteDialog = false"
        >
          Cancel
        </button>
        <button
          class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-lg shadow-lg"
          @click="del"
        >
          Delete
        </button>
      </div>
    </template>
  </el-dialog>
</template>
