import axios from "axios";

axios.defaults.headers.get["Access-Control-Allow-Origin"] = "*";
axios.defaults.headers.post["Access-Control-Allow-Origin"] = "*";

const service = axios.create({
  baseURL: "http://localhost:8080/", // api的base_url
  timeout: 5000, // 请求超时时间
});

// request拦截器
service.interceptors.request.use(
  (config) => {
    // 在这里你可以做一些请求前的操作
    // 如设置token
    // if (store.getters.token) {
    //   config.headers['Authorization'] = `Bearer ${store.getters.token}`;
    // }
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
    if (response.status !== 200) {
      console.error("error" + response); // for debug
    } else {
    }
    return response.data;
  },
  (error) => {
    // 处理响应错误
    console.error("err" + error); // for debug
    return Promise.reject(error);
  },
);

export default service;
