<script lang="ts" setup>
import { getUserAPI } from "@/apis/user";
import { UserInfo } from "@/types/auth/info";
// import { useUserStore } from "@/stores/user";
import { User } from "@/types/users";
import { ReadableDate } from "@/utils/date";
import { getHeightWithoutHeader } from "@/utils/responsive";
import { getUserRoleList } from "@/utils/role";
import router from "@/utils/router";
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
    }
);

</script>

<template>
    <section :class="'bg-gray-50 dark:bg-gray-900 h-[' + headerlessHeight + 'px]'">
        <div class="mx-auto max-w-screen-xl py-8 space-y-4" v-if="userData.userId">
            <h1 class="text-4xl font-bold">User Profile</h1>
            <div class="bg-white p-8 rounded-lg ring-0 ring-black shadow-lg max-w-screen-sm">
                <div class="flex space-x-4">
                    <div class="w-1/3 flex justify-items-center justify-center place-content-center">
                        <Avatar :name="userData.username" :size="100" variant="beam" />
                    </div>
                    <div class="w-2/3 space-y-4 text-nowrap">
                        <h2 class="text-2xl font-bold">{{ userData.username }}</h2>
                        <h3 class="text-lg font-semibold">Id: {{ userData.userId }}</h3>
                        <p class="text-gray-500">{{ userData.email }}</p>
                        <span class="flex flex-row space-x-2 select-none">
                            <div v-for="role in getUserRoleList(userData.role)" :key="role"
                                class="p-2 w-fit bg-pink-50 rounded-xl ring-0 ring-pink-200 shadow-md font-bold text-pink-800">
                                {{ role }}
                            </div>
                        </span>
                        <p class="text-gray-500">
                            Register at: {{ ReadableDate(userData.createdAt) }}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
