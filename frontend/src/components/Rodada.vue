<template>
  <md-layout md-align="center">
     <md-layout md-flex="50" md-flex-xsmall="100">
    
        <h3>Análise da rodada {{analise.rodada_id}}</h3>
      
    <md-card md-with-hover v-for="partida in analise.partidas" style="width: 600px">
      <md-card-content>
        <md-layout md-gutter>
          <md-layout>
            <md-avatar style="border-radius: 0">
              <img :src="partida.clubeCasa.escudos.medio"/>
            </md-avatar>
            <span>{{partida.clubeCasa.nome}}</span>
          </md-layout>
          <md-layout md-align="center">
            <div>
              <p>X</p>
            </div>
          </md-layout>
          <md-layout md-align="center">
             <span>{{partida.clubeVisitante.nome}}</span>
            <md-avatar style="border-radius: 0">
              <img :src="partida.clubeVisitante.escudos.medio"/>
            </md-avatar>
          </md-layout>
        </md-layout>
        <div class="tcenter">
          <p>{{partida.local}} - {{partida.data | formatDate}}</p>
        </div>
        <md-layout>
          <md-layout>
             <md-list>
              <md-list-item>
                <p>
                  Pontos: {{partida.clubeCasa.pontos.mediaPontos | formatNumber}}<br>
                  Pontos Casa: {{partida.clubeCasa.mandante.mediaPontos | formatNumber}}<br>
                </p>
              </md-list-item>
              <md-list-item>
                <md-progress class="vantagem-bar" :md-progress="partida.vantagemCasa"></md-progress>
              </md-list-item>
            </md-list>
          </md-layout>

          <md-layout>
            <md-list>
              <md-list-item>
                <p>
                  Pontos: {{partida.clubeVisitante.pontos.mediaPontos | formatNumber}}<br>
                  Pontos Fora: {{partida.clubeVisitante.visitante.mediaPontos | formatNumber}}<br>
                </p>
              </md-list-item>
              <md-list-item>
                <md-progress class="vantagem-bar" :md-progress="partida.vantagemVisitante"></md-progress>
              </md-list-item>
            </md-list>
          </md-layout>
        </md-layout>
      </md-card-content>
    </md-card>
     </md-layout>
  </md-layout>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
import Vue from 'vue'

Vue.filter('formatDate', function (value) {
  moment.locale('pt-br')
  if (value) {
    return moment(value).format('DD/MM/YYYY HH:mm')
  }
})

export default {
  name: 'rodada',
  data () {
    return {
      analise: {},
      errors: []
    }
  },
  created () {
    axios.get('/analise')
      .then(response => {
        this.analise = response.data
      })
      .catch(e => {
        this.errors.push(e)
      })
  }
}
</script>

<style scoped>
  .clube-selo {
    background-color: #24ab19;
  }
  .vantagem-bar {
    background-color: #e5e5e5 !important;
    height: 10px;
  }
  .tcenter {
    text-align: center;
  }

</style>
