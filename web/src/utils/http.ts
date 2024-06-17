import { BASE_URL } from "@/config";
import { useUserStore } from "@/stores/user";
import axios from "axios";
import { ElMessage } from "element-plus";
import router from "./router";

axios.defaults.headers.get["Access-Control-Allow-Origin"] = "*";
axios.defaults.headers.post["Access-Control-Allow-Origin"] = "*";

const service = axios.create({
  baseURL: BASE_URL, // api的base_url
  timeout: 5000, // 请求超时时间
});

const user = useUserStore();

// request拦截器
service.interceptors.request.use(
  (config) => {
    // 在这里你可以做一些请求前的操作
    // 如设置token
    // if (store.getters.token) {
    //   config.headers['Authorization'] = `Bearer ${store.getters.token}`;
    // }
    if (user.token) {
      config.headers["Authorization"] = `Bearer ${user.token.token}`;
    }
    return config;
  },
  (error) => {
    // 处理请求错误
    console.error(error); // for debug
    Promise.reject(error).then((r) => console.log(r));
  },
);

// response拦截器
service.interceptors.response.use(
  (response) => {
    // 在这里你可以对响应数据做一些处理
    if (response.data.code !== 200) {
      console.error("error" + response.data); // for debug
      ElMessage({
        message: response.data.message || "服务异常",
        type: "error",
      });
    } else {
    }
    return response.data;
  },
  (error) => {
    // 处理响应错误
    ElMessage({
      message: error.response.data.message || "服务异常",
      type: "error",
    });
    console.error("err" + error); // for debug
    if (error.response?.status === 401) {
      router.push("/login").then(() => {});
    }
    return Promise.reject(error);
  },
);

export default service;
