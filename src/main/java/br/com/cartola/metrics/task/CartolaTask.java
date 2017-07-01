package br.com.cartola.metrics.task;

import br.com.cartola.metrics.model.Rodada;
import br.com.cartola.metrics.model.cartola.AtletasResponse;
import br.com.cartola.metrics.model.cartola.MercadoResponse;
import br.com.cartola.metrics.model.cartola.PartidasResponse;
import br.com.cartola.metrics.repository.RodadaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartolaTask {

    private static final Logger log = LoggerFactory.getLogger(CartolaTask.class);

    @Autowired
    private RodadaRepository rodadaRepo;

    @Scheduled(fixedRate = 1000 * 60 * 3)
    public void checkMercado() {
        log.info("== Inicio Check de Mercado ==");

        MercadoResponse mercadoResponse = callMercado();

        AtletasResponse atletasResponse = callMercadoAtletas();

        Integer rodadaId = atletasResponse.getAtletas().get(0).getRodada_id();
        log.info("Rodada Atual: {}", rodadaId);

        if (mercadoResponse.getStatus_mercado() == 1
                && mercadoResponse.getRodada_atual() > rodadaId) {
            log.info("Mercado Aberto");

            PartidasResponse partidasResponse = callPartidas(rodadaId);

            Rodada rodada = new Rodada();
            rodada.setAtletas(atletasResponse.getAtletas());
            rodada.setPartidas(partidasResponse.getPartidas());
            rodada.setId(rodadaId);
            rodadaRepo.save(rodada);

        } else {
            log.info("Mercado Fechado");
        }

        log.info("== Fim Check de Mercado ==");
    }

    private MercadoResponse callMercado() {
        try {
            log.info("Mercado Status");
            return new RestTemplate()
                    .getForObject("https://api.cartolafc.globo.com/mercado/status",
                            MercadoResponse.class);

        } catch (Exception e) {
            log.error("Mercado Status: {}", e);
        }
        return null;
    }

    private AtletasResponse callMercadoAtletas() {
        try {
            log.info("Mercado Atletas");
            return new RestTemplate()
                    .getForObject("https://api.cartolafc.globo.com/atletas/mercado",
                            AtletasResponse.class);
        } catch (Exception e) {
            log.error("Mercado Atletas: {}", e);
        }
        return null;
    }

    private PartidasResponse callPartidas(Integer rodadaId) {
        try {
            log.info("Partidas");
            return new RestTemplate()
                    .getForObject("https://api.cartolafc.globo.com/partidas/" + rodadaId,
                            PartidasResponse.class);
        } catch (Exception e) {
            log.error("Partidas: {}", e);
        }
        return null;
    }
}
