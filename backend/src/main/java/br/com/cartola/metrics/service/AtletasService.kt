package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.AnaliseAtleta
import br.com.cartola.metrics.model.AnaliseRodada
import br.com.cartola.metrics.model.Atleta
import br.com.cartola.metrics.model.Status
import br.com.cartola.metrics.repository.AnaliseAtletaRepository
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class AtletasService(
        private val repository: AnaliseAtletaRepository,
        private val mongoTemplate: MongoTemplate
) {

    fun gerarAtletas(atletas: List<Atleta>, rodadaAtual: Long) {

        //so provaveis
        val provaveis = atletas
                .filter { it.status_id == Status.PROVAVEL.id }

        //media + jogos
        provaveis.forEach { it.peso = getMedia(it) * (2 + getJogos(it).toDouble() / 100) }

        // analise da rodada
        val query = Query()
        query.with(Sort(Sort.Direction.DESC, "_id"))
        query.limit(1)
        val analiseRodada = mongoTemplate.findOne(query, AnaliseRodada::class.java)

        analiseRodada.partidas.filter { it.valida }.forEach {
            val vantagemPair = if (it.vantagemCasa.compareTo(it.vantagemVisitante) == 1)
                Pair(it.vantagemCasa, it.clubeCasa!!.id)
            else Pair(it.vantagemVisitante, it.clubeVisitante!!.id)

            provaveis
                    .filter { it.clube_id == vantagemPair.second }
                    .forEach { it.peso = it.peso?.times(1 + vantagemPair.first / 100) }
        }

        //mais escalados

        // salva no banco
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