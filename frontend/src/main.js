import Vue from 'vue'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import App from './App'

/* eslint-disable no-new */
Vue.use(VueMaterial)
new Vue({
  el: '#app',
  template: '<App/>',
  components: { App }
})
