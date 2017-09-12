<template>
  <div class="ranking">
    <md-table-card>

      <md-toolbar>
        <h1>Ranking de clubes por pontuação do Cartola FC</h1>
        <span style="flex: 1"></span>
        <h4 v-if="clubes[0]">Rodada {{clubes[0].rodadas[clubes[0].rodadas.length -1].id}} - 2017</h4>
      </md-toolbar>

        <md-table md-sort="pontos.mediaPontos" md-sort-type="desc" @sort="sortBy">
          <md-table-header>
            <md-table-row>
              <md-table-head></md-table-head>
              <md-table-head></md-table-head>
              <md-table-head class="title-cell" md-sort-by="pontos.mediaPontos" md-tooltip="Média de Pontos">
                Pontos</md-table-head>
              <md-table-head class="title-cell" md-sort-by="pontos.mediaCedidos" md-tooltip="Média de Pontos Cedidos">
                Pontos Cedidos</md-table-head>
              <md-table-head class="title-cell" md-sort-by="mandante.mediaPontos" md-tooltip="Média de Pontos como Mandante">
                Pontos (Mandante)</md-table-head>
              <md-table-head class="title-cell" md-sort-by="mandante.mediaCedidos" md-tooltip="Média de Pontos Cedidos como Mandante">
                Cedidos (Mandante)</md-table-head>
              <md-table-head class="title-cell" md-sort-by="visitante.mediaPontos" md-tooltip="Média de Pontos como Visitante">
                Pontos (Visitante)</md-table-head>
              <md-table-head class="title-cell" md-sort-by="visitante.mediaCedidos" md-tooltip="Média de Pontos Cedidos como Visitante">Cedidos (Visitante)</md-table-head>
              
            </md-table-row>
          </md-table-header>

          <md-table-body>
            <template v-for="(clube, index) in clubes">
              <md-table-row>
                <md-table-cell md-numeric>{{index+1}}</md-table-cell>
                <md-table-cell class="clube-cell"><img :src="clube.escudos.pequeno"/> {{clube.nome}}</md-table-cell>
                <md-table-cell md-numeric>{{clube.pontos.mediaPontos | formatNumber}}</md-table-cell>
                <md-table-cell md-numeric>{{clube.pontos.mediaCedidos | formatNumber}}</md-table-cell>
                <md-table-cell md-numeric>{{clube.mandante.mediaPontos | formatNumber}}</md-table-cell>
                <md-table-cell md-numeric>{{clube.mandante.mediaCedidos | formatNumber}}</md-table-cell>
                <md-table-cell md-numeric>{{clube.visitante.mediaPontos | formatNumber}}</md-table-cell>
                <md-table-cell md-numeric>{{clube.visitante.mediaCedidos | formatNumber}}</md-table-cell>
                <md-table-cell>
                  <md-button class="md-icon-button md-raised md-dense" @click="selectItem(clube)">
                    <md-icon class="md-warn">show_chart</md-icon>
                  </md-button>
                </md-table-cell>
              </md-table-row>
              <md-table-row v-if="showChart == clube.id">
                <md-table-cell :colspan="9">
                  <clube-chart v-bind:data="clube" :height="70"></clube-chart>
                </md-table-cell>
              </md-table-row>
            </template>
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
  import ClubeChart from './ClubeChart'

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
    components: { ClubeChart },
    name: 'ranking',
    data () {
      return {
        clubes: [],
        errors: [],
        showChart: 0
      }
    },
    created () {
      axios.get('/clubes')
        .then(response => {
          this.clubes = response.data
          this.showChart = 0
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    methods: {
      sortBy (sort) {
        this.showChart = 0
        let props = sort.name.split('.')
        this.clubes = _.orderBy(this.clubes, [item => item[props[0]][props[1]]], sort.type)
      },
      selectItem (item) {
        this.showChart = item.id !== this.showChart ? item.id : 0
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
