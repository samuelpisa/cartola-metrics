package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.*
import br.com.cartola.metrics.repository.ClubeRepository
import br.com.cartola.metrics.repository.RodadaRepository
import br.com.cartola.metrics.service.dto.PontosClubeResult
import br.com.cartola.metrics.service.dto.RodadaTotalResults
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.query.Criteria.where

@Component
class RodadaTotalService {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate
    @Autowired
    private lateinit var clubeRepo: ClubeRepository
    @Autowired
    private lateinit var rodadaRepo: RodadaRepository

    fun totalizarRodada(rodadaId: Int) {

        val agg = newAggregation(
                match(where("_id").`is`(rodadaId)),
                project("atletas"),
                unwind("atletas"),
                group("atletas.clube_id")
                        .sum("atletas.pontos_num").`as`("total")
        )

        val groupResults = mongoTemplate.aggregate(agg, Rodada::class.java, RodadaTotalResults::class.java)
        val results = groupResults.mappedResults

        val clubes = clubeRepo.findAll()
        val rodadaAtual = rodadaRepo.findOne(rodadaId)

        clubes.forEach { c ->
            val rodadaTotal: RodadaTotalResults = results.first{ (id) -> id == c.id }
            val rodadaClube = RodadaClube()
            rodadaClube.id = rodadaId
            rodadaClube.pontos = rodadaTotal.total
            rodadaClube.valida = findPartida(rodadaAtual, c).valida
            rodadaClube.casa = findPartida(rodadaAtual, c).clube_casa_id == c.id
            rodadaClube.pontosCedidos = getPontosCedidos(results, rodadaClube.casa, findPartida(rodadaAtual, c))

            adicionaRodada(c, rodadaClube)
            clubeRepo.save(c)
        }

    }

    private fun findPartida(rodada: Rodada, clube: Clube): Partida =
            rodada.partidas.first { p -> p.clube_casa_id == clube.id || p.clube_visitante_id == clube.id }

    private fun getPontosCedidos(resultados: List<RodadaTotalResults>, casa: Boolean, partida: Partida): Double {
        val adversarioId: Int = if (casa) partida.clube_visitante_id!! else partida.clube_casa_id!!
        return resultados.first{ (id) -> id == adversarioId}.total
    }

    private fun adicionaRodada(clube: Clube, rodada: RodadaClube){
        clube.rodadas.removeIf { r -> r.id == rodada.id }
        clube.rodadas.add(rodada)

    }

    fun totalizarClube() {
        val clubes = clubeRepo.findAll()
        val totalPontos = totalPontosClubes()
        val totalMandante = totalPontosClubesMandante()
        val totalVisitante = totalPontosClubesVisitante()

        clubes.forEach { c ->
            val pc = totalPontos.first { (id) -> id == c.id }
            c.pontos = PontoClube(pc.mediaPontos, pc.totalPontos, pc.mediaCedidos, pc.totalCedidos)

            val pm = totalMandante.first { (id) -> id == c.id }
            c.mandante = PontoClube(pm.mediaPontos, pm.totalPontos, pm.mediaCedidos, pm.totalCedidos)

            val pv = totalVisitante.first { (id) -> id == c.id }
            c.visitante = PontoClube(pv.mediaPontos, pv.totalPontos, pv.mediaCedidos, pv.totalCedidos)

            clubeRepo.save(c)
        }
    }

    private fun totalPontosClubes(): List<PontosClubeResult> {
        val agg = newAggregation(
                unwind("rodadas"),
                match(where("rodadas.valida").`is`(true)),
                group("id")
                        .avg("rodadas.pontos").`as`("mediaPontos")
                        .sum("rodadas.pontos").`as`("totalPontos")
                        .avg("rodadas.pontosCedidos").`as`("mediaCedidos")
                        .sum("rodadas.pontosCedidos").`as`("totalCedidos")
        )
        return mongoTemplate
                .aggregate(agg, Clube::class.java, PontosClubeResult::class.java)
                .mappedResults
    }

    private fun totalPontosClubesMandante(): List<PontosClubeResult> {
        val agg = newAggregation(
                unwind("rodadas"),
                match(where("rodadas.valida").`is`(true).andOperator(where("rodadas.casa").`is`(true))),
                group("id")
                        .avg("rodadas.pontos").`as`("mediaPontos")
                        .sum("rodadas.pontos").`as`("totalPontos")
                        .avg("rodadas.pontosCedidos").`as`("mediaCedidos")
                        .sum("rodadas.pontosCedidos").`as`("totalCedidos")
        )
        return mongoTemplate
                .aggregate(agg, Clube::class.java, PontosClubeResult::class.java)
                .mappedResults
    }

    private fun totalPontosClubesVisitante(): List<PontosClubeResult> {
        val agg = newAggregation(
                unwind("rodadas"),
                match(where("rodadas.valida").`is`(true).andOperator(where("rodadas.casa").`is`(false))),
                group("id")
                        .avg("rodadas.pontos").`as`("mediaPontos")
                        .sum("rodadas.pontos").`as`("totalPontos")
                        .avg("rodadas.pontosCedidos").`as`("mediaCedidos")
                        .sum("rodadas.pontosCedidos").`as`("totalCedidos")
        )
        return mongoTemplate
                .aggregate(agg, Clube::class.java, PontosClubeResult::class.java)
                .mappedResults
    }
}
