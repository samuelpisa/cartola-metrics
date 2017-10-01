import Vue from 'vue'
import Router from 'vue-router'
import Hello from './components/Hello'
import Ranking from './components/Ranking'
import Rodada from './components/Rodada'
import Escalacao from './components/Escalacao'
import Sobre from './components/Sobre'

Vue.use(Router)

export default new Router({
  mode: 'history',
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
    },
    {
      path: '/analise-rodada',
      name: 'Rodada',
      component: Rodada
    },
    {
      path: '/escalacao-inteligente',
      name: 'Escalacao',
      component: Escalacao
    },
    {
      path: '/sobre',
      name: 'Sobre',
      component: Sobre
    }
  ]
})
