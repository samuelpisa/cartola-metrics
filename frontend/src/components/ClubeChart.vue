<script>
  import { Line } from 'vue-chartjs'
  import numeral from 'numeral'

  export default Line.extend({
    props: ['data'],
    mounted () {
      this.renderChart(formatData(this.data),
        {
          legend: {
            display: false
          },
          tooltips: {
            mode: 'nearest',
            callbacks: {
              title: function (item, data) {
                let comp = item[0].xLabel.replace('v', '- Visitante')
                comp = comp.replace('m', '- Mandante')
                return 'Rodada ' + comp
              },
              label: function (item, data) {
                return data.datasets[item.datasetIndex].label + ' ' + numeral(item.yLabel).format('0.00')
              }
            }
          }
        })
    }
  })

  function formatData (clube) {
    return {
      labels: clube.rodadas.filter(e => e.valida).map(e => e.id + (e.casa ? ' m' : ' v')),
      datasets: [
        {
          label: 'Pontos',
          data: clube.rodadas.filter(e => e.valida).map(e => e.pontos),
          fill: false,
          borderColor: '#24ab19',
          backgroundColor: '#24ab19',
          lineTension: 0,
          borderWidth: 2,
          showLine: true
        },
        {
          label: 'Pontos Cedidos',
          data: clube.rodadas.filter(e => e.valida).map(e => e.pontosCedidos),
          fill: false,
          borderColor: '#f87979',
          backgroundColor: '#f87979',
          lineTension: 0,
          borderWidth: 2
        },
        {
          label: 'Média',
          data: clube.rodadas.filter(e => e.valida).map(e => clube.pontos.mediaPontos),
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