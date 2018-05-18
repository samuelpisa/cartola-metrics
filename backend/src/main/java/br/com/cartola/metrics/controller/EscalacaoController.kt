package br.com.cartola.metrics.controller

import br.com.cartola.metrics.controller.response.AtletaEscalacaoResponse
import br.com.cartola.metrics.model.Atleta
import br.com.cartola.metrics.service.EscalacaoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EscalacaoController(
        private val escalacaoService: EscalacaoService
) {

    @GetMapping(value = ["/escalacao"])
    @ResponseBody
    private fun analise(@RequestParam("cartoletas") cartoletas: Double): List<AtletaEscalacaoResponse>? {

        val escalacoes = mutableListOf<List<Atleta>>()
        (1..400).forEach {
            escalacoes.add(escalacaoService.escalar(cartoletas))
        }

        val result = escalacoes
                .filterNot { it.isEmpty() }
                .maxBy { it.sumByDouble { it.peso!! } }
        return result?.map { AtletaEscalacaoResponse.from(it) }
    }
}