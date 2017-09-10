package br.com.cartola.metrics.controller

import br.com.cartola.metrics.model.Clube
import br.com.cartola.metrics.repository.ClubeRepository
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ClubeController(private val clubeRepo: ClubeRepository) {

    @GetMapping(value = "/clubes")
    @ResponseBody
    private fun clubes(): List<Clube> = clubeRepo.findAll(Sort(Sort.Direction.DESC, "pontos.mediaPontos"))
}