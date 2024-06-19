<script lang="ts" setup>
import { onMounted, ref, Ref } from "vue";
import {
  AirlineInfo,
  MerchantInfo,
  PassengerInfo,
  StaffInfo,
} from "@/types/auth/info";
import { BaseRegisterParams, RegisterParams } from "@/types/auth/register";
import {
  registerAirlineAPI,
  registerMerchantAPI,
  registerPassengerAPI,
  registerStaffAPI,
} from "@/apis/auth";
import TitleComp from "@/components/TitleComp.vue";
import router from "@/utils/router.ts";

const items = ["Passenger", "Merchant", "Airline", "Staff"];
let selectedIndex = ref(0);

const user: Ref<BaseRegisterParams> = ref({
  username: "",
  email: "",
  password: "",
});

const passengerInfo: Ref<PassengerInfo> = ref({
  passengerName: "",
  passengerPhone: "",
  identityNumber: "",
});
const merchantInfo: Ref<MerchantInfo> = ref({
  merchantName: "",
  merchantAddress: "",
  merchantDescription: "",
});
const airlineInfo: Ref<AirlineInfo> = ref({
  airlineName: "",
  airlineCode: "",
  airlineCountry: "",
  airlineDescription: "",
});
const staffInfo: Ref<StaffInfo> = ref({
  staffName: "",
});

const checkIntegrity = () => {
  if (
    user.value.username === "" ||
    user.value.email === "" ||
    user.value.password === ""
  ) {
    return false;
  }
  if (selectedIndex.value === 0) {
    return (
      passengerInfo.value.passengerName !== "" &&
      passengerInfo.value.passengerPhone !== "" &&
      passengerInfo.value.identityNumber !== ""
    );
  }
  if (selectedIndex.value === 1) {
    return (
      merchantInfo.value.merchantName !== "" &&
      merchantInfo.value.merchantAddress !== "" &&
      merchantInfo.value.merchantDescription !== ""
    );
  }
  if (selectedIndex.value === 2) {
    return (
      airlineInfo.value.airlineName !== "" &&
      airlineInfo.value.airlineCode !== "" &&
      airlineInfo.value.airlineCountry !== "" &&
      airlineInfo.value.airlineDescription !== ""
    );
  }
  if (selectedIndex.value === 3) {
    return staffInfo.value.staffName !== "";
  }
  return false;
};

const register = async () => {
  if (!checkIntegrity()) {
    return;
  }

  const registerAPI = async (data: any) => {
    const res = await data.api({
      ...user.value,
      data: data.info.value,
    } as RegisterParams<any>);
    if (res !== null) {
      await router.push("/login");
    }
  };

  const registerFunctions = [
    { api: registerPassengerAPI, info: passengerInfo },
    { api: registerMerchantAPI, info: merchantInfo },
    { api: registerAirlineAPI, info: airlineInfo },
    { api: registerStaffAPI, info: staffInfo },
  ];

  await registerAPI(registerFunctions[selectedIndex.value]);
};

const selectItem = (index: number) => {
  selectedIndex.value = index;
};

const isLargeScreen = ref(window.innerWidth > 1024);

onMounted(() => {
  window.addEventListener("resize", () => {
    isLargeScreen.value = window.innerWidth > 1024;
  });
});
</script>

<template>
  <section class="bg-gray-50 dark:bg-gray-900">
    <div
      class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0"
    >
      <TitleComp />
      <div
        v-if="isLargeScreen"
        class="flex flex-col bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-6xl xl:p-0 dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="w-full pt-6 px-8">
          <h1
            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"
          >
            Create an account
          </h1>
        </div>
        <div class="w-full p-6 flex flex-row">
          <div class="space-y-3 md:space-y-6 w-full">
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="email"
                >Your email</label
              >
              <input
                id="username"
                v-model="user.username"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="username"
                required
                type="text"
              />
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="email"
                >Your email</label
              >
              <input
                id="email"
                v-model="user.email"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="email"
                placeholder="name@company.com"
                required
                type="email"
              />
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="password"
                >Password</label
              >
              <input
                id="password"
                v-model="user.password"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="password"
                placeholder="••••••••"
                required
                type="password"
              />
            </div>
            <!-- <div class="flex items-start">
              <div class="flex items-center h-5">
                <input id="terms"
                  class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-pink-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-pink-600 dark:ring-offset-gray-800"
                  required type="checkbox" />
              </div>
              <div class="ml-3 text-sm">
                <label class="font-light text-gray-500 dark:text-gray-300" for="terms">I accept the
                  <a class="font-medium text-pink-600 hover:underline dark:text-pink-500" href="#">Terms and
                    Conditions</a></label>
              </div>
            </div> -->
            <button
              class="w-full text-white bg-pink-600 hover:bg-pink-700 focus:ring-4 focus:outline-none focus:ring-pink-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-pink-600 dark:hover:bg-pink-700 dark:focus:ring-pink-800"
              @click="register"
            >
              Create an account
            </button>
            <p class="text-sm font-light text-gray-500 dark:text-gray-400">
              Already have an account?
              <router-link to="/login">
                <span
                  class="font-medium text-pink-600 hover:underline dark:text-pink-500"
                  >Login here</span
                >
              </router-link>
            </p>
          </div>
          <div class="p-4">
            <div class="h-full border-r border-gray-200 dark:border-gray-700" />
          </div>
          <div class="w-full space-y-4">
            <div>
              <label
                class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                >Register as</label
              >
              <ul
                class="flex text-sm font-medium text-center text-gray-500 rounded-md shadow dark:divide-gray-700 dark:text-gray-400"
              >
                <li
                  v-for="(item, index) in items"
                  :key="index"
                  class="w-full focus-within:z-10"
                >
                  <span
                    :class="[
                      'inline-block w-full px-4 py-2 border-r border-gray-200 dark:border-gray-700 focus:ring-4 focus:ring-blue-300 focus:outline-none',
                      selectedIndex === index
                        ? 'text-gray-900 bg-gray-100 dark:bg-gray-700 dark:text-white'
                        : 'bg-white hover:text-gray-700 hover:bg-gray-50 dark:hover:text-white dark:bg-gray-800 dark:hover:bg-gray-700',
                      index === 0
                        ? 'rounded-s-lg'
                        : index === items.length - 1
                          ? 'rounded-e-lg border-s-0'
                          : '',
                    ]"
                    @click="selectItem(index)"
                  >
                    {{ item }}
                  </span>
                </li>
              </ul>
            </div>

            <template v-if="selectedIndex === 0">
              <!-- Passenger 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Passenger Name</label
                >
                <input
                  v-model="passengerInfo.passengerName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Passenger Phone</label
                >
                <input
                  v-model="passengerInfo.passengerPhone"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="tel"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Identity Number</label
                >
                <input
                  v-model="passengerInfo.identityNumber"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>

            <template v-if="selectedIndex === 1">
              <!-- Merchant 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Merchant Name</label
                >
                <input
                  v-model="merchantInfo.merchantName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Merchant Address</label
                >
                <input
                  v-model="merchantInfo.merchantAddress"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Merchant Description</label
                >
                <input
                  v-model="merchantInfo.merchantDescription"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>

            <template v-if="selectedIndex === 2">
              <!-- Airline 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Name</label
                >
                <input
                  v-model="airlineInfo.airlineName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Code</label
                >
                <input
                  v-model="airlineInfo.airlineCode"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Country</label
                >
                <input
                  v-model="airlineInfo.airlineCountry"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Description</label
                >
                <input
                  v-model="airlineInfo.airlineDescription"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>
            <template v-if="selectedIndex === 3">
              <!-- Staff 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Staff Name</label
                >
                <input
                  v-model="staffInfo.staffName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>
          </div>
        </div>
      </div>
      <div
        v-else
        class="flex flex-col bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-6xl xl:p-0 dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="w-full pt-6 px-8">
          <h1
            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"
          >
            Create an account
          </h1>
        </div>
        <div class="w-full p-6 flex flex-col space-y-4">
          <div class="space-y-3 md:space-y-6 w-full">
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="email"
                >Your email</label
              >
              <input
                id="username"
                v-model="user.username"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="username"
                required
                type="text"
              />
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="email"
                >Your email</label
              >
              <input
                id="email"
                v-model="user.email"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="email"
                placeholder="name@company.com"
                required
                type="email"
              />
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                for="password"
                >Password</label
              >
              <input
                id="password"
                v-model="user.password"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-pink-600 focus:border-pink-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                name="password"
                placeholder="••••••••"
                required
                type="password"
              />
            </div>
            <!-- <div class="flex items-start">
              <div class="flex items-center h-5">
                <input id="terms"
                  class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-pink-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-pink-600 dark:ring-offset-gray-800"
                  required type="checkbox" />
              </div>
              <div class="ml-3 text-sm">
                <label class="font-light text-gray-500 dark:text-gray-300" for="terms">I accept the
                  <a class="font-medium text-pink-600 hover:underline dark:text-pink-500" href="#">Terms and
                    Conditions</a></label>
              </div>
            </div> -->
          </div>
          <div class="w-full space-y-4">
            <div>
              <label
                class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                >Register as</label
              >
              <ul
                class="flex text-sm font-medium text-center text-gray-500 rounded-md shadow dark:divide-gray-700 dark:text-gray-400"
              >
                <li
                  v-for="(item, index) in items"
                  :key="index"
                  class="w-full focus-within:z-10"
                >
                  <span
                    :class="[
                      'inline-block w-full px-4 py-2 border-r border-gray-200 dark:border-gray-700 focus:ring-4 focus:ring-blue-300 focus:outline-none',
                      selectedIndex === index
                        ? 'text-gray-900 bg-gray-100 dark:bg-gray-700 dark:text-white'
                        : 'bg-white hover:text-gray-700 hover:bg-gray-50 dark:hover:text-white dark:bg-gray-800 dark:hover:bg-gray-700',
                      index === 0
                        ? 'rounded-s-lg'
                        : index === items.length - 1
                          ? 'rounded-e-lg border-s-0'
                          : '',
                    ]"
                    @click="selectItem(index)"
                  >
                    {{ item }}
                  </span>
                </li>
              </ul>
            </div>

            <template v-if="selectedIndex === 0">
              <!-- Passenger 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Passenger Name</label
                >
                <input
                  v-model="passengerInfo.passengerName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Passenger Phone</label
                >
                <input
                  v-model="passengerInfo.passengerPhone"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="tel"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Identity Number</label
                >
                <input
                  v-model="passengerInfo.identityNumber"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>
            <template v-if="selectedIndex === 1">
              <!-- Merchant 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Merchant Name</label
                >
                <input
                  v-model="merchantInfo.merchantName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Merchant Address</label
                >
                <input
                  v-model="merchantInfo.merchantAddress"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Merchant Description</label
                >
                <input
                  v-model="merchantInfo.merchantDescription"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>
            <template v-if="selectedIndex === 2">
              <!-- Airline 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Name</label
                >
                <input
                  v-model="airlineInfo.airlineName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Code</label
                >
                <input
                  v-model="airlineInfo.airlineCode"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Country</label
                >
                <input
                  v-model="airlineInfo.airlineCountry"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Airline Description</label
                >
                <input
                  v-model="airlineInfo.airlineDescription"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>
            <template v-if="selectedIndex === 3">
              <!-- Staff 表单 -->
              <div>
                <label
                  class="block text-sm mb-2 font-medium text-gray-900 dark:text-white"
                  >Staff Name</label
                >
                <input
                  v-model="staffInfo.staffName"
                  class="w-full px-4 py-2 border rounded-md dark:text-gray-900"
                  type="text"
                />
              </div>
            </template>
          </div>
          <div class="py-4 space-y-4">
            <button
              class="w-full text-white bg-pink-600 hover:bg-pink-700 focus:ring-4 focus:outline-none focus:ring-pink-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-pink-600 dark:hover:bg-pink-700 dark:focus:ring-pink-800"
              @click="register"
            >
              Create an account
            </button>
            <p class="text-sm font-light text-gray-500 dark:text-gray-400">
              Already have an account?
              <router-link to="/login">
                <span
                  class="font-medium text-pink-600 hover:underline dark:text-pink-500"
                  >Login here</span
                >
              </router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
