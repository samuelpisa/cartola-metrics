import Vue from 'vue'
import Router from 'vue-router'
import Hello from './components/Hello'
import Ranking from './components/Ranking'

Vue.use(Router)

export default new Router({
  linkExactActiveClass: 'is-active',
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/ranking-clubes',
      name: 'Ranking',
      component: Ranking
    }
  ]
})
