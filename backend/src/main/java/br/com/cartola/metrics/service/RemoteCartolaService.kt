package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.cartola.AtletasResponse
import br.com.cartola.metrics.model.cartola.MercadoResponse
import br.com.cartola.metrics.model.cartola.PartidasResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RemoteCartolaService {

    fun callMercado(): MercadoResponse {
        log.info("Mercado Status {}", URL_MERCADO)
        return try {
            val rest = RestTemplate()
            val headers = HttpHeaders()
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
            val entity = HttpEntity("", headers)
            val response = rest.exchange<MercadoResponse>(URL_MERCADO, HttpMethod.GET, entity, MercadoResponse::class.java)
            return response.body
        } catch (e: Exception) {
            log.error("Mercado Status: {}", e)
            MercadoResponse()
        }
    }

    fun callMercadoAtletas(): AtletasResponse {
        log.info("Mercado Atletas {}", URL_ATLETAS)
        return try {
            val rest = RestTemplate()
            val headers = HttpHeaders()
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
            val entity = HttpEntity("", headers)
            val response = rest.exchange<AtletasResponse>(URL_ATLETAS, HttpMethod.GET, entity, AtletasResponse::class.java)
            return response.body
        } catch (e: Exception) {
            log.error("Mercado Atletas: {}", e)
            AtletasResponse()
        }
    }

    fun callPartidas(rodadaId: Int): PartidasResponse {
        log.info("Partidas {}", URL_PARTIDAS)
        return try {
            val rest = RestTemplate()
            val headers = HttpHeaders()
            headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
            val entity = HttpEntity("", headers)
            val response = rest.exchange<PartidasResponse>("$URL_PARTIDAS/$rodadaId", HttpMethod.GET, entity, PartidasResponse::class.java)
            return response.body
        } catch (e: Exception) {
            log.error("Partidas: {}", e)
            PartidasResponse()
        }
    }

    companion object {
        private const val URL_MERCADO = "https://api.cartolafc.globo.com/mercado/status"
        private const val URL_ATLETAS = "https://api.cartolafc.globo.com/atletas/mercado"
        private const val URL_PARTIDAS = "https://api.cartolafc.globo.com/partidas"
        private val log = LoggerFactory.getLogger(RemoteCartolaService::class.java)
    }
}