package br.com.cartola.metrics.task

import br.com.cartola.metrics.service.AnaliseRodadaService
import br.com.cartola.metrics.service.AtletasService
import br.com.cartola.metrics.service.RemoteCartolaService
import br.com.cartola.metrics.service.RodadaMercadoService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CartolaTask(
        private val remoteCartolaService: RemoteCartolaService,
        private val rodadaService: RodadaMercadoService,
        private val analiseService: AnaliseRodadaService,
        private val atletasService: AtletasService) {

    @Scheduled(fixedRate = (1000 * 60 * 60).toLong())
    fun checkMercado() {
        log.info("= Inicio Check de Mercado =")

        val mercadoResponse = remoteCartolaService.callMercado()
        val atletasResponse = remoteCartolaService.callMercadoAtletas()

        if (mercadoResponse.status_mercado == 1L && atletasResponse.atletas.first().scout != null) {
            log.info("Mercado Aberto")

            val rodadaId: Int = atletasResponse.atletas.first().rodada_id ?: 0
            rodadaService.adicionarRodada(atletasResponse.atletas, remoteCartolaService.callPartidas(rodadaId).partidas)
        } else {
            log.info("Mercado Fechado")
        }
        log.info("= Fim Check de Mercado =")
    }

    @Scheduled(fixedRate = (1000 * 60 * 60).toLong())
    fun checkPartidas() {
        log.info("= Inicio Check de Partidas =")

        val mercadoResponse = remoteCartolaService.callMercado()
        val partidas = remoteCartolaService.callPartidas(mercadoResponse.rodada_atual.toInt())
        analiseService.gerarAnalise(partidas)

        log.info("= Fim Check de Partidas =")
    }

    @Scheduled(fixedRate = (1000 * 60 * 30).toLong(), initialDelay = (1000 * 20).toLong())
    fun checkAtletas(){
        log.info("= Inicio Check de Atletas =")
        val mercadoResponse = remoteCartolaService.callMercado()
        val atletasResponse = remoteCartolaService.callMercadoAtletas()

        if (mercadoResponse.status_mercado == 1L && atletasResponse.atletas.first().scout != null) {
            log.info("Mercado Aberto")
            log.info("Rodada Atual {}", mercadoResponse.rodada_atual)
            log.info("Qtde atletas {}", atletasResponse.atletas.size)
            atletasService.gerarAtletas(atletasResponse.atletas, mercadoResponse.rodada_atual)
        } else {
            log.info("Mercado Fechado")
        }
        log.info("= Fim Check de Atletas =")
    }


    companion object {
        private val log = LoggerFactory.getLogger(CartolaTask::class.java)
    }
}
