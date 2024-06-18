<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { TableV2FixedDir } from "element-plus";

import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import ResizableTableComp from "@/components/ResizableTableComp.vue";
import { createRequestRequest, getRequestResponse } from "@/types/request";
import {
  cancelRequestAPI,
  createRequestAPI,
  getRequestsListAPI,
  handleRequestAPI,
  resolveRequestAPI,
} from "@/apis/requests";
import { DateRender } from "@/utils/date";

const columns = [
  {
    key: "ids",
    title: "ID",
    dataKey: "ids",
    width: 30,
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
    key: "userName",
    title: "User Name",
    dataKey: "username",
    width: 100,
    align: "center",
  },
  {
    key: "info",
    title: "Info",
    dataKey: "info",
    width: 300,
    align: "center",
  },
  {
    key: "status",
    title: "Status",
    dataKey: "status",
    width: 100,
    align: "center",
    cellRenderer: ({ cellData: status }: { cellData: string }) => {
      if (status === "unhandled")
        return <div class="text-red-500 font-bold">Unhandled</div>;
      if (status === "handled")
        return <div class="text-yellow-500 font-bold">Handled</div>;
      if (status === "resolved")
        return <div class="text-green-500 font-bold">Resolved</div>;
      if (status === "canceled")
        return <div class="text-gray-500 font-bold">Canceled</div>;
      return status;
    },
  },
  {
    key: "handlerId",
    title: "Handler ID",
    dataKey: "handlerId",
    width: 100,
    align: "center",
    cellRenderer: ({ cellData: handlerId }: { cellData: number }) => {
      if (!handlerId) return "Unassigned";
      return handlerId;
    },
  },
  {
    key: "handlerName",
    title: "Handler Name",
    dataKey: "handlerName",
    width: 100,
    align: "center",
    cellRenderer: ({ cellData: handlerName }: { cellData: string }) => {
      if (!handlerName) return "Unassigned";
      return handlerName;
    },
  },
  {
    key: "createdAt",
    title: "Created At",
    dataKey: "createdAt",
    width: 200,
    align: "center",
    cellRenderer: ({ cellData: updatedAt }: { cellData: string }): string =>
      DateRender(updatedAt),
  },
  {
    key: "updatedAt",
    title: "Updated At",
    dataKey: "updatedAt",
    width: 200,
    align: "center",
    cellRenderer: ({ cellData: updatedAt }: { cellData: string }): string =>
      DateRender(updatedAt),
  },
  {
    key: "operations",
    title: "Operations",
    cellRenderer: ({ rowData }: any) => (
      <div class={"flex flex-row space-x-2"}>
        <button
          v-show={rowData.status === "handled"}
          class={
            "bg-blue-500 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-blue-600"
          }
          id={rowData.ids}
          onClick={() => {
            resolve(rowData.ids);
          }}
        >
          Resolve
        </button>
        <button
          v-show={rowData.status === "unhandled"}
          class={
            "bg-green-500 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-green-600"
          }
          id={rowData.ids}
          onClick={() => {
            handle(rowData.ids);
          }}
        >
          Handle
        </button>
        <button
          v-show={
            !(rowData.status === "canceled" || rowData.status === "resolved")
          }
          class={
            "bg-pink-500 rounded-lg py-1 px-2 select-none cursor-pointer text-white hover:font-bold hover:bg-pink-600"
          }
          id={rowData.ids}
          onClick={() => {
            cancel(rowData.ids);
          }}
        >
          Cancel
        </button>
      </div>
    ),
    width: 300,
    align: "center",
    fixed: TableV2FixedDir.RIGHT,
  },
];

const data: Ref<getRequestResponse[]> = ref<getRequestResponse[]>([]);
const page = ref(1);
const allCount = ref(0);

const getData = async (page: number) => {
  const pagination: Pagination = {
    page: page - 1,
    count: 10,
  };
  const res = await getRequestsListAPI(pagination);
  if (!res || res.length === 0) return;
  data.value = res;
};

const createDialog = ref(false);

const requestsData = ref({
  id: 0,
  info: "",
});

const add = async () => {
  // sanity check
  if (requestsData.value.info === "") {
    return;
  }
  const params: createRequestRequest = { ...requestsData.value };
  await createRequestAPI(params);
  createDialog.value = false;
  clean();
};

const handle = async (id: number) => {
  await handleRequestAPI(id);
  clean();
};

const resolve = async (id: number) => {
  await resolveRequestAPI(id);
  clean();
};

const cancel = async (id: number) => {
  await cancelRequestAPI(id);
  clean();
};

const clean = () => {
  getData(page.value);
  requestsData.value = { id: 0, info: "" };
};

onMounted(async () => {
  allCount.value = await getTypesCountStatistic("request");

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
  <div :style="'height:' + headerlessHeight + 'px'" class="ml-72 p-8">
    <div
      class="mx-auto p-8 max-w-screen-xl w-full h-full shadow-lg rounded-lg flex flex-col space-y-4"
    >
      <div class="flex">
        <h1 class="text-2xl font-bold">Request Management</h1>
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
          :data="data"
          :total="allCount"
          @paginationChange="paginationChange"
        />
      </div>
    </div>
  </div>

  <el-dialog
    v-model="createDialog"
    :draggable="true"
    align-center
    title="Create a Request"
    width="500"
  >
    <el-form :model="requestsData" label-width="120px">
      <el-form-item label="Info">
        <el-input v-model="requestsData.info" placeholder="Enter info" />
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
</template>
