package br.com.cartola.metrics.service

import br.com.cartola.metrics.model.Atleta
import br.com.cartola.metrics.model.Partida
import br.com.cartola.metrics.model.Rodada
import br.com.cartola.metrics.repository.RodadaRepository
import br.com.cartola.metrics.task.CartolaTask
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RodadaMercadoService(
        private val totalService: RodadaTotalService,
        private val rodadaRepo: RodadaRepository
) {

    fun adicionarRodada(atletas: List<Atleta>, partidas: List<Partida>) {

        val rodadaId: Int = atletas.first().rodada_id
        log.info("Adicionar rodada: {}", rodadaId)

        if (rodadaRepo.count() < rodadaId) {
            val rodada = Rodada()
            rodada.atletas = atletas
            rodada.partidas = partidas
            rodada.id = rodadaId
            rodadaRepo.save(rodada)
            log.info("Rodada {} adicionada", rodadaId)

            totalService.totalizarRodada(rodadaId)
            log.info("Rodada {} totalizada", rodadaId)

            totalService.totalizarClube()
            log.info("Pontos clube totalizado", rodadaId)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(RodadaMercadoService::class.java)
    }
}
