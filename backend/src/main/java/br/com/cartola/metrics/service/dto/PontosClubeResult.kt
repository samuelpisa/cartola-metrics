package br.com.cartola.metrics.service.dto

data class PontosClubeResult(
    val id: Int? = null,
    val mediaPontos: Double = 0.0,
    val totalPontos: Double = 0.0,
    val mediaCedidos: Double = 0.0,
    val totalCedidos: Double = 0.0
)
