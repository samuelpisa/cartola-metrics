<template>
  <div id="app">
    <!--<md-toolbar class="md-medium md-warn">
      <div class="md-toolbar-container">
        <h1 class="md-display-2 toolbar-title" style="flex: 1;">Cartola Top</h1>
      </div>
    </md-toolbar>-->
    <ranking></ranking>
  </div>
</template>

<script>
import Hello from './components/Hello'
import Ranking from './components/Ranking'

export default {
  name: 'app',
  components: {
    Hello,
    Ranking
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
  .toolbar-title {
    color: #ffffff !important;
  }
</style>
