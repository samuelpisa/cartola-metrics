<script>
  import { Line } from 'vue-chartjs'

  export default Line.extend({
    props: ['data', 'options'],
    options: {
      animation: {
        duration: 0
      }
    },
    mounted () {
      this.renderChart(formatData(this.data), this.options)
    }
  })

  function formatData (clube) {
    return {
      labels: clube.rodadas.map(e => e.id),
      datasets: [
        {
          label: 'Pontos',
          data: clube.rodadas.map(e => e.pontos),
          fill: false,
          borderColor: '#24ab19',
          backgroundColor: '#24ab19',
          lineTension: 0,
          borderWidth: 2,
          showLine: true
        },
        {
          label: 'Pontos Cedidos',
          data: clube.rodadas.map(e => e.pontosCedidos),
          fill: false,
          borderColor: '#f87979',
          backgroundColor: '#f87979',
          lineTension: 0,
          borderWidth: 2
        },
        {
          label: 'Média',
          data: clube.rodadas.map(e => clube.pontos.mediaPontos),
          fill: false,
          borderColor: '#3f51b5',
          backgroundColor: '#3f51b5',
          lineTension: 0,
          pointRadius: 0,
          borderWidth: 2
        }
      ]
    }
  }

</script>