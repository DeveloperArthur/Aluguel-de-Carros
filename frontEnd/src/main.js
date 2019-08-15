import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import VueRouter from 'vue-router'
import {routes} from './routes'
import {store} from './store'
import '@mdi/font/css/materialdesignicons.css'

Vue.use(Vuetify);
Vue.use(VueRouter);

const router = new VueRouter({
  routes,
  mode:'history'
})

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App),
}).$mount('#app');