<script lang="tsx" setup>
import { getTypesCountStatistic } from "@/apis/statistic";
import { Pagination } from "@/types/types";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { onMounted, ref, Ref } from "vue";
import { TableV2FixedDir } from "element-plus";
import {
  createGoodsAPI,
  deleteGoodsAPI,
  getGoodsListAPI,
  updateGoodsAPI,
} from "@/apis/goods";
import {
  createGoodsRequest,
  getGoodsResponse,
  updateGoodsRequest,
} from "@/types/goods";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";

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

const createDialog = ref(false);
const deleteDialog = ref(false);
const modifyDialog = ref(false);

const goodsData = ref({
  id: 0,
  name: "",
  description: "",
  price: 0,
  stock: 0,
  category: "",
});

const add = async () => {
  // sanity check
  if (goodsData.value.name === "" || goodsData.value.price === 0) {
    return;
  }
  const params: createGoodsRequest = { ...goodsData.value };
  await createGoodsAPI(params);
  createDialog.value = false;
  clean();
};

const del = async () => {
  await deleteGoodsAPI(goodsData.value.id);
  deleteDialog.value = false;
  clean();
};

const modify = async () => {
  const params: updateGoodsRequest = { ...goodsData.value };
  await updateGoodsAPI(goodsData.value.id, params);
  modifyDialog.value = false;
  clean();
};

const sync = (id: number) => {
  const row = data.value.find((v) => v.ids === id);
  goodsData.value = { id: row.ids, ...row };
};

const clean = () => {
  goodsData.value = {
    id: 0,
    name: "",
    description: "",
    price: 0,
    stock: 0,
    category: "",
  };
  getData(page.value);
};

onMounted(async () => {
  allCount.value = await getTypesCountStatistic("goods");

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
      <div class="flex">
        <h1 class="text-2xl font-bold">Goods Details</h1>
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
    align-center
    title="Create a Goods"
    width="500"
  >
    <el-form :model="goodsData" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="goodsData.name"></el-input>
      </el-form-item>
      <el-form-item label="Description">
        <el-input v-model="goodsData.description"></el-input>
      </el-form-item>
      <el-form-item label="Price">
        <el-input-number v-model="goodsData.price"></el-input-number>
      </el-form-item>
      <el-form-item label="Stock">
        <el-input-number v-model="goodsData.stock"></el-input-number>
      </el-form-item>
      <el-form-item label="Category">
        <el-input v-model="goodsData.category"></el-input>
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
    align-center
    title="Modify Ticket"
    width="500"
  >
    <el-form :model="goodsData" label-width="80px">
      <el-form-item label="Name">
        <el-input v-model="goodsData.name"></el-input>
      </el-form-item>
      <el-form-item label="Description">
        <el-input v-model="goodsData.description"></el-input>
      </el-form-item>
      <el-form-item label="Price">
        <el-input-number v-model="goodsData.price"></el-input-number>
      </el-form-item>
      <el-form-item label="Stock">
        <el-input-number v-model="goodsData.stock"></el-input-number>
      </el-form-item>
      <el-form-item label="Category">
        <el-input v-model="goodsData.category"></el-input>
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
    align-center
    title="Delete Ticket"
    width="500"
  >
    <p class="text-lg text-red-500 font-semibold">
      Are you sure you want to delete this goods?
    </p>
    <p class="text-lg text-red-500 font-semibold">
      All data that depends on this goods will be deleted.
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
