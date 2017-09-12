package br.com.cartola.metrics.task

import br.com.cartola.metrics.model.cartola.AtletasResponse
import br.com.cartola.metrics.model.cartola.MercadoResponse
import br.com.cartola.metrics.model.cartola.PartidasResponse
import br.com.cartola.metrics.service.RodadaMercadoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CartolaTask(private val rodadaService: RodadaMercadoService) {

    @Scheduled(fixedRate = (1000 * 60 * 60).toLong())
    fun checkMercado() {
        log.info("= Inicio Check de Mercado =")

        val mercadoResponse = callMercado()
        val atletasResponse = callMercadoAtletas()

        if (mercadoResponse.status_mercado == 1L && atletasResponse.atletas.first().scout != null) {
            log.info("Mercado Aberto")

            val rodadaId: Int = atletasResponse.atletas.first().rodada_id ?: 0
            rodadaService.adicionarRodada(atletasResponse.atletas, callPartidas(rodadaId).partidas)
        } else {
            log.info("Mercado Fechado")
        }
        log.info("= Fim Check de Mercado =")
    }

    private fun callMercado(): MercadoResponse {
        log.info("Mercado Status")
        return try {
            RestTemplate().getForObject("https://api.cartolafc.globo.com/mercado/status", MercadoResponse::class.java)
        } catch (e: Exception) {
            log.error("Mercado Status: {}", e)
            MercadoResponse()
        }
    }

    private fun callMercadoAtletas(): AtletasResponse {
        log.info("Mercado Atletas")
        return try {
            RestTemplate().getForObject("https://api.cartolafc.globo.com/atletas/mercado", AtletasResponse::class.java)
        } catch (e: Exception) {
            log.error("Mercado Atletas: {}", e)
            AtletasResponse()
        }
    }

    private fun callPartidas(rodadaId: Int): PartidasResponse {
        log.info("Partidas")
        return try {
            RestTemplate().getForObject("https://api.cartolafc.globo.com/partidas/" + rodadaId, PartidasResponse::class.java)
        } catch (e: Exception) {
            log.error("Partidas: {}", e)
            PartidasResponse()
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(CartolaTask::class.java)
    }
}
