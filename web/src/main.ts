import { createApp } from "vue";
import { createPinia } from "pinia";
import "./style.css";
import App from "./App.vue";
import router from "./utils/router.ts";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import "element-plus/dist/index.css";
import ElementPlus from "element-plus";

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(router);
app.use(pinia);
app.use(ElementPlus);

app.mount("#app");
