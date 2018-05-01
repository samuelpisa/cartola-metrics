package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.AnaliseAtleta
import br.com.cartola.metrics.model.Atleta
import br.com.cartola.metrics.model.Status
import br.com.cartola.metrics.repository.AnaliseAtletaRepository
import org.springframework.stereotype.Component

@Component
class AtletasService(
        private val repository: AnaliseAtletaRepository
) {

    fun gerarAtletas(atletas: List<Atleta>, rodadaAtual: Long) {

        val provaveis = atletas
                .filter { it.status_id == Status.PROVAVEL.id }

        provaveis.forEach { it.peso = getMedia(it) * (1 + getJogos(it).toDouble() / 38) }

        val analise = AnaliseAtleta()
        analise.atletas = provaveis.sortedByDescending { it.peso }.toMutableList()
        analise.rodada_id = rodadaAtual.toInt()

        repository.delete(analise.rodada_id)
        repository.save(analise)
    }

    private fun getMedia(atleta: Atleta): Double =
            if (atleta.media_num!!.compareTo(0.toDouble()) == -1 ||
                    atleta.media_num!!.compareTo(0.toDouble()) == 0)
                0.1 else atleta.media_num ?: 0.1

    private fun getJogos(atleta: Atleta): Int = if (atleta.jogos_num == 0) 1 else atleta.jogos_num ?: 1
}