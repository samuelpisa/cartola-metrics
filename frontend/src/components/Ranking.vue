<template>
  <div class="ranking">
    <md-table-card>

      <md-toolbar>
        <h1 class="md-title">Ranking de clubes por pontuação do Cartola FC</h1>
        <md-button-toggle md-single class="md-primary">
          <md-button class="md-toggle">Todos os jogos</md-button>
          <md-button disabled>Jogos em casa</md-button>
          <md-button disabled>Jogos Fora</md-button>
        </md-button-toggle>
      </md-toolbar>

        <md-table md-sort="mediaPontos" md-sort-type="desc" @sort="sortBy">
          <md-table-header>
            <md-table-row>
              <md-table-head></md-table-head>
              <md-table-head></md-table-head>
              <md-table-head class="title-cell" md-sort-by="mediaPontos">Média de Pontos</md-table-head>
              <md-table-head class="title-cell">Total de Pontos</md-table-head>
              <md-table-head class="title-cell" md-sort-by="mediaCedidos">Média de Pontos Cedidos</md-table-head>
              <md-table-head class="title-cell">Total de Pontos Cedidos</md-table-head>
            </md-table-row>
          </md-table-header>

          <md-table-body>
            <md-table-row v-for="(clube, index) in clubes">
              <md-table-cell md-numeric>{{index+1}}</md-table-cell>
              <md-table-cell class="clube-cell"><img :src="clube.escudos.pequeno"/> {{clube.nome}}</md-table-cell>
              <md-table-cell md-numeric>{{clube.pontos.mediaPontos | formatNumber}}</md-table-cell>
              <md-table-cell md-numeric>{{clube.pontos.totalPontos | formatNumber}}</md-table-cell>
              <md-table-cell md-numeric>{{clube.pontos.mediaCedidos | formatNumber}}</md-table-cell>
              <md-table-cell md-numeric>{{clube.pontos.totalCedidos | formatNumber}}</md-table-cell>
            </md-table-row>
          </md-table-body>
        </md-table>
    </md-table-card>
  </div>
</template>

<script>
  import Vue from 'vue'
  import axios from 'axios'
  import numeral from 'numeral'
  import _ from 'lodash'

  numeral.register('locale', 'pt-BR', {
    delimiters: {
      decimal: ','
    }
  })
  numeral.locale('pt-BR')

  Vue.filter('formatNumber', function (value) {
    return numeral(value).format('0.00')
  })

  export default {
    components: {},
    name: 'ranking',
    data () {
      return {
        clubes: [],
        errors: []
      }
    },
    created () {
      axios.get('http://www.cartola.top/clubes')
        .then(response => {
          this.clubes = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    methods: {
      sortBy (sort) {
        this.clubes = _.orderBy(this.clubes, [item => item.pontos[sort.name]], sort.type)
      }
    }
  }
</script>

<style scoped>
  .clube-cell {
    text-align: left;
  }

  .title-cell {
    text-align: center;
  }

</style>
