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

        analiseRepository.save(analise)
    }

    private fun calcularDiferencaPontos(casa: Clube, visitante: Clube): Double? {
        val pontosCasa = (casa.pontos?.mediaPontos ?: 0.0) + (casa.mandante?.mediaPontos ?: 0.0)
        val pontosVisi = (visitante.pontos?.mediaPontos ?: 0.0) + (visitante.visitante?.mediaPontos ?: 0.0)
        return pontosCasa - pontosVisi
    }

}