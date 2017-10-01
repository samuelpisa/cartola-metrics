<template>
  <md-layout md-gutter md-align="center">
    
    <md-layout md-flex="40" md-flex-xsmall="100">
      <md-card>
          <md-card-header>
              <div class="md-title">Bem vindo ao Cartola Top</div>
          </md-card-header>
          <md-card-content>
            <p>
              O Cartola Top é um site com o objetivo de ajudar os jogadores de Cartola FC, com dicas e estatísticas
                mais detalhadas e analíticas sobre os clubes e jogadores do Campeonato Brasileiro. 
            </p>
          </md-card-content>
      </md-card>
    </md-layout>

    <md-layout md-flex="40" md-flex-xsmall="100">
      <md-card>
        <md-card-header>
          <md-card-header-text>
            <div class="md-title"><router-link :to="{ name: 'Ranking' }">Ranking de Clubes</router-link></div>
          </md-card-header-text>
        </md-card-header>
        <md-card-media md-ratio="16:9" md-medium>
          <img src="static//img//ranking-card.png">
        </md-card-media>
        <md-card-content>
          <p>
            Ranking de clubes do Campeonato Brasileiro que leva em consideração os pontos conquistados
            pelos jogadores no Cartola FC.
          </p>
        </md-card-content>
      </md-card>
    </md-layout>

    <md-layout md-flex="40" md-flex-xsmall="100">
      <md-card>
        <md-card-header>
          <md-card-header-text>
            <div class="md-title"><router-link :to="{ name: 'Rodada' }" >Análise da Rodada</router-link></div>
          </md-card-header-text>
        </md-card-header>
        <md-card-media md-ratio="16:9" md-medium>
          <img src="static//img//campo-card.png">
        </md-card-media>
        <md-card-content>
        </md-card-content>
      </md-card>
    </md-layout>

    <md-layout md-flex="40" md-flex-xsmall="100">
      <md-card>
        <md-card-header>
          <md-card-header-text>
            <div class="md-title"><router-link :to="{ name: 'Escalacao' }">Escalação Inteligente</router-link></div>
          </md-card-header-text>
        </md-card-header>
        <md-card-media md-ratio="16:9" md-medium>
          <img src="static//img//campo-card.png">
        </md-card-media>
        <md-card-content>
        </md-card-content>
      </md-card>
    </md-layout>
  </md-layout>
</template>

<script>
export default {
  name: 'hello'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
