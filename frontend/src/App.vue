<template>
  <div id="app">
   
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
      <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        <h2>Cartola Top</h2>
      </div>
      <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
      <div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">

        <router-link :to="{ name: 'Hello' }" class="mdl-layout__tab">Início</router-link>
        <router-link :to="{ name: 'Ranking' }" class="mdl-layout__tab">Ranking de Clubes</router-link>
        <router-link :to="{ name: 'Rodada' }" class="mdl-layout__tab">Análise da Rodada</router-link>
        <router-link :to="{ name: 'Escalacao' }" class="mdl-layout__tab">Escalação Inteligente</router-link>
        <router-link :to="{ name: 'Sobre' }" class="mdl-layout__tab">Sobre</router-link>
        
      </div>
    </header>
    <main class="mdl-layout__content">

      <router-view></router-view>

      <footer class="mdl-mega-footer">
        <div class="mdl-mega-footer--middle-section">
          <div class="mdl-mega-footer--drop-down-section">
            <input class="mdl-mega-footer--heading-checkbox" type="checkbox" checked>
            <h1 class="mdl-mega-footer--heading">Site</h1>
            <ul class="mdl-mega-footer--link-list">
              <li><router-link :to="{ name: 'Hello' }">Início</router-link></li>
              <li><router-link :to="{ name: 'Ranking' }">Ranking de Clubes</router-link></li>
              <li><router-link :to="{ name: 'Rodada' }">Análise da Rodada</router-link></li>
              <li><router-link :to="{ name: 'Escalacao' }">Escalação Inteligente</router-link></li>
              <li><router-link :to="{ name: 'Sobre' }">Sobre</router-link></li>
            </ul>
          </div>
          <div class="mdl-mega-footer--drop-down-section">
            <input class="mdl-mega-footer--heading-checkbox" type="checkbox" checked>
            <h1 class="mdl-mega-footer--heading">Sobre</h1>
            <ul class="mdl-mega-footer--link-list">
              <li>O Cartola Top é um site com o objetivo de ajudar os jogadores de Cartola FC, com dicas e estatísticas
                mais detalhadas e analíticas sobre os clubes e jogadores do Campeonato Brasileiro. 
                <br>O Cartola Top não é afiliado ao Cartola FC.
              </li>
            </ul>
          </div>
          <div class="mdl-mega-footer--drop-down-section">
            <input class="mdl-mega-footer--heading-checkbox" type="checkbox" checked>
            <h1 class="mdl-mega-footer--heading">Contato</h1>
            <ul class="mdl-mega-footer--link-list">
              <li></li>
            </ul>
          </div>
        </div>
        <div class="mdl-mega-footer--bottom-section">
          <div class="mdl-logo">
            Cartola Top. Todos os direitos reservados. &#9400; 2017
          </div>
        </div>
      </footer>
    </main>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
/*#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
  .toolbar-title {
    color: #ffffff !important;
  }*/
</style>
