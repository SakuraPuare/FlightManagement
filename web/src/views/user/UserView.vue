<script lang="ts" setup>
import {
    getAirlineAPI,
    getMerchantAPI,
    getPassengerAPI,
    getStaffAPI,
    getUserAPI,
} from "@/apis/user";
import { UserInfo } from "@/types/auth/info";
import { Airline, Merchant, Passenger, Staff } from "@/types/users";
// import { useUserStore } from "@/stores/user";
import { ReadableDate } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import {
    getUserRoleList,
    isAirline,
    isMerchant,
    isPassenger,
    isStaff,
} from "@/utils/role";
import router from "@/utils/router";
import { faAccessibleIcon } from "@fortawesome/free-brands-svg-icons";
import {
    faBuilding,
    faPlane,
    faUserTie,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { watch } from "vue";
import { onMounted, ref, Ref } from "vue";
import Avatar from "vue-boring-avatars";

const headerlessHeight = ref(0); // Define the headerlessHeight property

const parseId = (id: string) => {
    console.log("id", id);
    const parsedId = parseInt(id);
    if (isNaN(parsedId)) {
        router.push("/404");
        console.error("Invalid user id");
    }
    return parsedId;
};

const update = async () => {
    try {
        const res = await getUserAPI(id.value);
        if (res === null) {
            console.error("User not found");
            router.push("/404");
        } else {
            userData.value = res;
        }
        const role = userData.value.role;
        userRoleList.value = getUserRoleList(role);
        if (isAirline(role)) {
            airlineData.value = await getAirlineAPI(id.value);
        }
        if (isMerchant(role)) {
            merchantData.value = await getMerchantAPI(id.value);
        }
        if (isPassenger(role)) {
            passengerData.value = await getPassengerAPI(id.value);
        }
        if (isStaff(role)) {
            staffData.value = await getStaffAPI(id.value);
        }
    } catch (error) {
        console.error("Error fetching user:", error);
        router.push("/");
    }
};

const id_ = router.currentRoute.value.params.id;
const id = ref(parseId(id_ as string));

// const user = useUserStore();
let userData: Ref<UserInfo> = ref({
    userId: 0,
    username: "",
    email: "",
    role: 0,
    createdAt: "",
    updatedAt: "",
});

let userRoleList: Ref<string[]> = ref([]);

let airlineData: Ref<Airline> = ref({
    airlineId: 0,
    userId: 0,
    airlineName: "",
    airlineCountry: "",
    airlineCode: "",
    airlineDescription: "",
    createdAt: "",
    updatedAt: "",
});

let merchantData: Ref<Merchant> = ref({
    merchantId: 0,
    userId: 0,
    merchantName: "",
    merchantAddress: "",
    merchantDescription: "",
    createdAt: "",
    updatedAt: "",
});

let passengerData: Ref<Passenger> = ref({
    passengerId: 0,
    userId: 0,
    passengerName: "",
    passengerPhone: "",
    identityNumber: "",
    createdAt: "",
    updatedAt: "",
});

let staffData: Ref<Staff> = ref({
    staffId: 0,
    userId: 0,
    staffName: "",
    createdAt: "",
    updatedAt: "",
});

onMounted(async () => {
    headerlessHeight.value = getHeightWithoutHeader();
    window.addEventListener("resize", () => {
        headerlessHeight.value = getHeightWithoutHeader();
    });

    update();
});

watch(
    () => router.currentRoute.value.params.id,
    (newId) => {
        // console.log("newId", newId);
        newId = newId as string;
        if (isNaN(parseInt(newId))) {
            router.push("/404");
            console.error("Invalid user id");
        }
        id.value = parseInt(newId);
        update();
    },
);
</script>

<template>
    <section :class="'max-h-[' + headerlessHeight + 'px] h-full'">
        <div v-if="userData.userId" class="mx-auto max-w-screen-xl py-8 space-y-4">
            <h1 class="text-4xl font-bold">Profile</h1>
            <div class="flex flex-row flex-wrap">
                <div class="bg-white rounded-lg py-8 pl-8 pr-16 ring-0 ring-black shadow-lg max-w-screen-md w-fit m-6">
                    <div class="flex space-x-4">
                        <div class="w-1/3 flex justify-items-center justify-center place-content-center">
                            <Avatar :name="userData.username" :size="96" variant="beam" />
                        </div>
                        <div class="w-2/3 space-y-4 text-nowrap">
                            <h2 class="text-2xl font-bold">User {{ userData.username }}</h2>
                            <h3 class="text-lg font-semibold">Id: {{ userData.userId }}</h3>
                            <p class="text-gray-500">{{ userData.email }}</p>
                            <div class="flex flex-row space-x-2 select-none">
                                <div v-for="role in userRoleList" :key="role"
                                    class="p-2 w-fit bg-pink-50 rounded-xl ring-0 ring-pink-200 shadow-md font-bold text-pink-800">
                                    {{ role }}
                                </div>
                            </div>
                            <p class="text-gray-500">
                                Register at: {{ ReadableDate(userData.createdAt) }}
                            </p>
                        </div>
                    </div>
                </div>
                <div v-if="isMerchant(userData.role)"
                    class="bg-white p-8 pr-4 m-6 rounded-lg ring-0 ring-black shadow-lg max-w-screen-lg">
                    <div class="flex space-x-4">
                        <div class="w-1/3 mx-auto flex justify-center">
                            <div class="bg-gray-200 rounded-full w-24 h-24 flex justify-center items-center">
                                <FontAwesomeIcon :icon="faPlane" class="text-4xl h-[60%] text-pink-500" />
                            </div>
                        </div>
                        <div class="w-2/3 space-y-4 text-nowrap">
                            <h3 class="text-xl font-bold">
                                Airline {{ airlineData.airlineName }}
                            </h3>
                            <p class="text-gray-500 text-wrap">
                                {{ airlineData.airlineDescription }}
                            </p>
                            <p class="text-gray-500">
                                Country: {{ airlineData.airlineCountry }}
                            </p>
                            <p class="text-gray-500">Code: {{ airlineData.airlineCode }}</p>
                            <p class="text-gray-500">
                                Register at: {{ ReadableDate(airlineData.createdAt) }}
                            </p>
                        </div>
                    </div>
                </div>
                <div v-if="isMerchant(userData.role)"
                    class="bg-white p-8 m-6 rounded-lg ring-0 ring-black shadow-lg max-w-screen-lg">
                    <div class="flex space-x-4">
                        <div class="w-1/3 mx-auto flex justify-center">
                            <div class="bg-gray-200 rounded-full w-24 h-24 flex justify-center items-center">
                                <FontAwesomeIcon :icon="faBuilding" class="text-4xl h-[60%] text-blue-500" />
                            </div>
                        </div>
                        <div class="w-2/3 space-y-4 text-nowrap">
                            <h3 class="text-xl font-bold">
                                Merchant {{ merchantData.merchantName }}
                            </h3>
                            <p class="text-gray-500 text-wrap">
                                {{ merchantData.merchantDescription }}
                            </p>
                            <p class="text-gray-500">
                                Address: {{ merchantData.merchantAddress }}
                            </p>
                            <p class="text-gray-500">
                                Register at: {{ ReadableDate(merchantData.createdAt) }}
                            </p>
                        </div>
                    </div>
                </div>
                <div v-if="isPassenger(userData.role)"
                    class="bg-white p-8 m-6 rounded-lg ring-0 ring-black shadow-lg max-w-screen-lg">
                    <div class="flex space-x-4">
                        <div class="w-1/3 mx-auto flex justify-center">
                            <div class="bg-gray-200 rounded-full w-24 h-24 flex justify-center items-center">
                                <FontAwesomeIcon :icon="faAccessibleIcon" class="text-4xl h-[60%] text-blue-500" />
                            </div>
                        </div>
                        <div class="w-2/3 space-y-4 text-nowrap">
                            <h3 class="text-xl font-bold">
                                Passenger {{ passengerData.passengerName }}
                            </h3>
                            <p class="text-gray-500">
                                Phone: {{ passengerData.passengerPhone }}
                            </p>
                            <p class="text-gray-500">
                                Identity Number: {{ passengerData.identityNumber }}
                            </p>
                            <p class="text-gray-500">
                                Register at: {{ ReadableDate(passengerData.createdAt) }}
                            </p>
                        </div>
                    </div>
                </div>
                <div v-if="isStaff(userData.role)"
                    class="bg-white p-8 m-6 rounded-lg ring-0 ring-black shadow-lg max-w-screen-lg">
                    <div class="flex space-x-4">
                        <div class="w-1/3 mx-auto flex justify-center">
                            <div class="bg-gray-200 rounded-full w-24 h-24 flex justify-center items-center">
                                <FontAwesomeIcon :icon="faUserTie" class="text-4xl h-[60%] text-purple-500" />
                            </div>
                        </div>
                        <div class="w-2/3 space-y-4">
                            <h3 class="text-xl font-bold">Staff {{ staffData.staffName }}</h3>
                            <p class="text-gray-500">
                                Register at: {{ ReadableDate(staffData.createdAt) }}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
