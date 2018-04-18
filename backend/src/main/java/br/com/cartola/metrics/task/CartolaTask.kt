package br.com.cartola.metrics.task

import br.com.cartola.metrics.model.cartola.AtletasResponse
import br.com.cartola.metrics.model.cartola.MercadoResponse
import br.com.cartola.metrics.model.cartola.PartidasResponse
import br.com.cartola.metrics.service.AnaliseRodadaService
import br.com.cartola.metrics.service.RodadaMercadoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CartolaTask(
        private val rodadaService: RodadaMercadoService,
        private val analiseService: AnaliseRodadaService) {

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

    @Scheduled(fixedRate = (1000 * 60 * 60).toLong())
    fun checkPartidas() {
        log.info("= Inicio Check de Partidas =")

        val mercadoResponse = callMercado()
        val partidas = callPartidas(mercadoResponse.rodada_atual.toInt())
        analiseService.gerarAnalise(partidas)

        log.info("= Fim Check de Partidas =")
    }

    private fun callMercado(): MercadoResponse {
        log.info("Mercado Status")
        return try {
            val rest = RestTemplate()
            val headers = HttpHeaders()
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
            val entity = HttpEntity("", headers)
            val response = rest.exchange<MercadoResponse>("https://api.cartolafc.globo.com/mercado/status", HttpMethod.GET, entity, MercadoResponse::class.java)
            return response.body
        } catch (e: Exception) {
            log.error("Mercado Status: {}", e)
            MercadoResponse()
        }
    }

    private fun callMercadoAtletas(): AtletasResponse {
        log.info("Mercado Atletas")
        return try {
            val rest = RestTemplate()
            val headers = HttpHeaders()
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
            val entity = HttpEntity("", headers)
            val response = rest.exchange<AtletasResponse>("https://api.cartolafc.globo.com/atletas/mercado", HttpMethod.GET, entity, AtletasResponse::class.java)
            return response.body
        } catch (e: Exception) {
            log.error("Mercado Atletas: {}", e)
            AtletasResponse()
        }
    }

    private fun callPartidas(rodadaId: Int): PartidasResponse {
        log.info("Partidas")
        return try {
            val rest = RestTemplate()
            val headers = HttpHeaders()
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
            val entity = HttpEntity("", headers)
            val response = rest.exchange<PartidasResponse>("https://api.cartolafc.globo.com/partidas/$rodadaId", HttpMethod.GET, entity, PartidasResponse::class.java)
            return response.body
        } catch (e: Exception) {
            log.error("Partidas: {}", e)
            PartidasResponse()
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(CartolaTask::class.java)
    }
}
