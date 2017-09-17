import Vue from 'vue'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import App from './App'
import router from './routes'

/* eslint-disable no-new */
Vue.use(VueMaterial)
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
