<template>
  <div class="ranking">
    <md-card>
      <md-card-header>
        <div class="md-title">Ranking de clubes por pontuação do Cartola FC</div>
      </md-card-header>
      <md-card-content>
        <md-table md-sort="pontos.mediaPontos">
          <md-table-header>
            <md-table-row>
              <md-table-head></md-table-head>
              <md-table-head></md-table-head>
              <md-table-head class="title-cell" md-sort-by="pontos.mediaPontos">Média de Pontos</md-table-head>
              <md-table-head class="title-cell">Total de Pontos</md-table-head>
              <md-table-head class="title-cell" md-sort-by="pontos.mediaCedidos">Média de Pontos Cedidos</md-table-head>
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
      </md-card-content>
    </md-card>
  </div>
</template>

<script>
  import Vue from 'vue'
  import axios from 'axios'
  import numeral from 'numeral'

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
      axios.get('/clubes')
        .then(response => {
          this.clubes = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
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
