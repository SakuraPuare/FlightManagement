<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { DateRender } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref, watch } from "vue";
import { TableV2FixedDir } from "element-plus";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { getPassengersListAPI } from "@/apis/user";
import { Passenger } from "@/types/users";
import ResizableTableComp from "@/components/ResizableTableComp.vue";
import { createLuggageRequest, getLuggageResponse } from "@/types/luggage";
import {
  createLuggageAPI,
  deleteLuggageAPI,
  getLuggagesListAPI,
  updateLuggageAPI,
} from "@/apis/luggages";
import { getOrderResponse } from "@/types/order";
import { getOrdersListByUserIdAPI } from "@/apis/orders";

const columns = [
  {
    key: "ids",
    title: "ID",
    dataKey: "ids",
    width: 50,
    align: "center",
  },
  {
    key: "userId",
    title: "User ID",
    dataKey: "userId",
    width: 100,
    align: "center",
  },
  {
    key: "username",
    title: "Username",
    dataKey: "username",
    width: 100,
    align: "center",
  },
  {
    key: "orderId",
    title: "Order ID",
    dataKey: "orderId",
    width: 100,
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
    key: "staffName",
    title: "Staff Name",
    dataKey: "staffName",
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
        </button>
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

const luggageData = ref({
  id: 0,
  userId: 0,
  orderId: 0,
  weight: 0,
});

const add = async () => {
  // sanity check
  if (
    luggageData.value.userId === 0 ||
    luggageData.value.orderId === 0 ||
    luggageData.value.weight === 0
  ) {
    return;
  }
  const params: createLuggageRequest = { ...luggageData.value };
  await createLuggageAPI(params);
  createDialog.value = false;
  clean();
};

const del = async () => {
  await deleteLuggageAPI(luggageData.value.id);
  deleteDialog.value = false;
  clean();
};

const modify = async () => {
  await updateLuggageAPI(luggageData.value.id, luggageData.value);
  modifyDialog.value = false;
  clean();
};

const sync = (id: number) => {
  const row = tableData.value.find((item) => item.ids === id);
  if (!row) return;
  luggageData.value = { id: row.ids, ...row };
};

const clean = () => {
  getTableData(tablePage.value);
  luggageData.value = {
    id: 0,
    userId: 0,
    orderId: 0,
    weight: 0,
  };
};

const tableData: Ref<getLuggageResponse[]> = ref<getLuggageResponse[]>([]);
const tablePage = ref(1);
const tableCount = ref(0);
const passengerList = ref<Passenger[]>([]);
const passengerPage = ref(1);
const passengerCount = ref(0);
const orderList = ref<getOrderResponse>([]);

watch(
  () => luggageData.value.userId,
  async (v) => {
    if (v != 0) {
      const res = await getOrdersListByUserIdAPI(v);
      if (!res || res.length === 0) return;
      orderList.value = res;
    } else {
      orderList.value = [];
    }
    console.log(orderList.value);
  },
);

const getTableData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getLuggagesListAPI(pagination);
  if (!res || res.length === 0) return;
  tableData.value = res;
};

const getPassengerData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 100,
  };
  const res = await getPassengersListAPI(pagination);
  if (!res || res.length === 0) return;
  passengerList.value.push(...res);
};

onMounted(async () => {
  tableCount.value = await getTypesCountStatistic("luggage");
  passengerCount.value = await getTypesCountStatistic("passenger");
  await getTableData(tablePage.value);
  while ((passengerPage.value - 1) * 100 < passengerCount.value) {
    await getPassengerData(passengerPage.value++);
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
  <div :style="'height:' + headerlessHeight + 'px'" class="ml-0 lg:ml-72 p-8">
    <div
      class="mx-auto p-8 max-w-screen-xl w-full h-full shadow-lg rounded-lg flex flex-col space-y-4"
    >
      <div class="flex">
        <h1 class="text-2xl font-bold">Luggage Management</h1>
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
    title="Create Luggage"
    width="600"
  >
    <el-form :model="luggageData" label-width="100px">
      <el-form-item label="User ID">
        <el-select v-model="luggageData.userId">
          <el-option
            v-for="passenger in passengerList"
            :key="passenger.userId"
            :label="passenger.passengerName"
            :value="passenger.userId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Order ID">
        <el-select v-model="luggageData.orderId">
          <el-option
            v-for="order in orderList"
            :key="order.ids"
            :label="`${order.flightNumber} - FROM ${order.departureCity} TO ${order.arrivalCity}`"
            :value="order.ids"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Weight">
        <el-input v-model="luggageData.weight" />
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
    title="Modify Luggage"
    width="600"
  >
    <el-form :model="luggageData" label-width="100px">
      <el-form-item label="User ID">
        <el-select v-model="luggageData.userId">
          <el-option
            v-for="passenger in passengerList"
            :key="passenger.userId"
            :label="passenger.passengerName"
            :value="passenger.userId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Order ID">
        <el-select v-model="luggageData.orderId">
          <el-option
            v-for="order in orderList"
            :key="order.ids"
            :label="`${order.flightNumber} - FROM ${order.departureCity} TO ${order.arrivalCity}`"
            :value="order.ids"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Weight">
        <el-input v-model="luggageData.weight" />
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
    title="Delete Luggage"
    width="500"
  >
    <p class="text-lg text-red-500 font-semibold">
      Are you sure you want to delete this luggage?
    </p>
    <p class="text-lg text-red-500 font-semibold">
      All data that depends on this luggage will be deleted.
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
