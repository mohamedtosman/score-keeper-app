import Vue from 'vue'
import App from './App.vue'
import router from "./routes";
import VueApexCharts from 'vue-apexcharts'
import vSelect from 'vue-select'


Vue.config.productionTip = false;
Vue.use(VueApexCharts);
Vue.component('apexchart', VueApexCharts);
Vue.component('v-select', vSelect);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
