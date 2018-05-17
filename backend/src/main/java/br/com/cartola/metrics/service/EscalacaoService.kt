package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.AnaliseAtleta
import br.com.cartola.metrics.model.Atleta
import br.com.cartola.metrics.model.Posicao
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class EscalacaoService(
        private val mongoTemplate: MongoTemplate
) {

    fun escalar(cartoletas: Double): MutableList<Atleta> {

        // analise da rodada
        val query = Query()
        query.with(Sort(Sort.Direction.DESC, "_id"))
        query.limit(1)
        val analiseAtleta = mongoTemplate.findOne(query, AnaliseAtleta::class.java)
        val atletas = analiseAtleta.atletas

        val medioTime = atletas.sumByDouble { it.preco_num!! } / atletas.size * 12
        val limite = if (cartoletas > medioTime) 2 else 1

        if (cartoletas < medioTime / 2)
            return mutableListOf()

        var escalacao: MutableList<Atleta>
        do {
            escalacao = mutableListOf()
            escalacao.addAll(takeAtleta(atletas, Posicao.GOLEIRO, 1, limite))
            escalacao.addAll(takeAtleta(atletas, Posicao.LATERAL, 2, limite))
            escalacao.addAll(takeAtleta(atletas, Posicao.ZAGUEIRO, 2, limite))
            escalacao.addAll(takeAtleta(atletas, Posicao.MEIA, 3, limite))
            escalacao.addAll(takeAtleta(atletas, Posicao.ATACANTE, 3, limite))
            escalacao.addAll(takeAtleta(atletas, Posicao.TECNICO, 1, limite))
        } while (escalacao.sumByDouble { it.preco_num!! }.compareTo(cartoletas) == 1)

        println("\n\nPeso Time: ${escalacao.sumByDouble { it.peso!! }}")
        println("Preco Time: ${escalacao.sumByDouble { it.preco_num!! }}")
        escalacao.forEach { print(it.apelido + ", ") }

        return escalacao

    }

    private fun takeAtleta(atletas: List<Atleta>, posicao: Posicao, quantidade: Int, limite: Int): List<Atleta> {
        return atletas.filter { it.posicao_id == posicao.id }
                .sortedByDescending { it.peso }
                .take(atletas.count { it.posicao_id == posicao.id } / limite)
                .shuffled()
                .take(quantidade)
    }
}