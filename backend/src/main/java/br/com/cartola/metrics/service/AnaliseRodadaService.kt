package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.AnalisePartida
import br.com.cartola.metrics.model.AnaliseRodada
import br.com.cartola.metrics.model.Clube
import br.com.cartola.metrics.model.cartola.PartidasResponse
import br.com.cartola.metrics.repository.AnaliseRodadaRepository
import br.com.cartola.metrics.repository.ClubeRepository
import org.springframework.stereotype.Component

@Component
class AnaliseRodadaService(
        private val analiseRepository: AnaliseRodadaRepository,
        private val clubeRepository: ClubeRepository) {

    fun gerarAnalise(partidaResponse: PartidasResponse) {

        val clubes = clubeRepository.findAll()

        var analise = AnaliseRodada()
        analise.rodada_id = partidaResponse.rodada
        partidaResponse.partidas.forEach { p ->

            val clubeCasa = clubes.first { c -> c.id == p.clube_casa_id }
            val clubeVisi = clubes.first { c -> c.id == p.clube_visitante_id }

            val analisePartida = AnalisePartida()
            analisePartida.clubeCasa = clubeCasa
            analisePartida.clubeVisitante = clubeVisi
            analisePartida.valida = p.valida
            analisePartida.local = p.local
            analisePartida.data = p.partida_data

            var dif = calcularDiferencaPontos(casa = clubeCasa, visitante = clubeVisi) ?: 0.0
            analisePartida.seloCasa = dif > 0.0
            analisePartida.diferencaPontos = Math.abs(dif)

            analise.partidas.add(analisePartida)
        }

        calculaVantagem(analise.partidas)

        analiseRepository.save(analise)
    }

    private fun calcularDiferencaPontos(casa: Clube, visitante: Clube): Double? {
        val rodadasAnalisar = 6

        val pontosCasa = casa.rodadas.filter {it.valida}.takeLast(rodadasAnalisar).sumByDouble { it.pontos } +
                casa.rodadas.filter { it.valida && it.casa }.takeLast(rodadasAnalisar).sumByDouble { it.pontos }

        val pontosVisi = visitante.rodadas.filter {it.valida}.takeLast(rodadasAnalisar).sumByDouble { it.pontos } +
                visitante.rodadas.filter { it.valida && !it.casa }.takeLast(rodadasAnalisar).sumByDouble { it.pontos }

        return pontosCasa - pontosVisi
    }

    private fun calculaVantagem(partidas: List<AnalisePartida>) {
        val maxDiff = partidas.map { Math.abs(it.diferencaPontos) }.sortedDescending().first()

        partidas.filter{it.valida}.forEach{ p ->
            if(p.seloCasa)
                p.vantagemCasa = p.diferencaPontos / maxDiff * 100
            else
                p.vantagemVisitante = Math.abs(p.diferencaPontos) / maxDiff * 100
        }
    }

}