package br.com.cartola.metrics.controller

import br.com.cartola.metrics.model.AnaliseRodada
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AnaliseController(private val mongoTemplate: MongoTemplate) {

    @GetMapping(value = "/analise")
    @ResponseBody
    private fun analise(): AnaliseRodada {
        val query = Query()
        query.with(Sort(Sort.Direction.DESC, "_id"))
        query.limit(1)
        var analise = mongoTemplate.findOne(query, AnaliseRodada::class.java)
        analise.partidas.sortByDescending { it.diferencaPontos }
        analise.partidas.sortByDescending { it.valida }
        return analise
    }
}