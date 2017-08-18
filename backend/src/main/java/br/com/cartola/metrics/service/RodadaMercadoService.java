package br.com.cartola.metrics.service;

import br.com.cartola.metrics.model.Atleta;
import br.com.cartola.metrics.model.Partida;
import br.com.cartola.metrics.model.Rodada;
import br.com.cartola.metrics.repository.RodadaRepository;
import br.com.cartola.metrics.task.CartolaTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RodadaMercadoService {

    private static final Logger log = LoggerFactory.getLogger(RodadaMercadoService.class);

    @Autowired
    private RodadaTotalService totalService;

    @Autowired
    private RodadaRepository rodadaRepo;

    public void adicionarRodada(List<Atleta> atletas, List<Partida> partidas) {

        Integer rodadaId = atletas.stream().findFirst().get().getRodada_id();
        log.info("Adicionar rodada: {}", rodadaId);

        if (rodadaRepo.count() < rodadaId) {
            Rodada rodada = new Rodada();
            rodada.setAtletas(atletas);
            rodada.setPartidas(partidas);
            rodada.setId(rodadaId);
            rodadaRepo.save(rodada);
            log.info("Rodada {} adicionada", rodadaId);

            totalService.totalizarRodada(rodadaId);
            log.info("Rodada {} totalizada", rodadaId);

            totalService.totalizarClube();
            log.info("Pontos clube totalizado", rodadaId);
        }
    }
}
