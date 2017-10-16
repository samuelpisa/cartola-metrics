<template>
      <div>
      {{analise.rodada_id}}

    <md-card v-for="partida in analise.partidas">
      <md-card-content>
        <p :class="partida.seloCasa ? 'clube-selo' : ''"><img :src="partida.clubeCasa.escudos.pequeno"/>{{partida.clubeCasa.nome}}</p>
        x
        <p :class="partida.seloCasa ? '' : 'clube-selo'"><img :src="partida.clubeVisitante.escudos.pequeno"/>{{partida.clubeVisitante.nome}}</p>

        <p>Local: {{partida.local}}</p>
        <p>valida: {{partida.valida}}</p>
      </md-card-content>
    </md-card>
      </div>
</template>

<script>
import axios from 'axios'

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
</style>
