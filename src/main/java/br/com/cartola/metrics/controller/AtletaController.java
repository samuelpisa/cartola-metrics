package br.com.cartola.metrics.controller;

import br.com.cartola.metrics.model.cartola.AtletasResponse;
import br.com.cartola.metrics.model.cartola.MercadoResponse;
import br.com.cartola.metrics.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Created by samuel on 30/05/17.
 */
@RestController
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping(value = "/")
    private String getAll(){

        MercadoResponse mercado = new RestTemplate()
                .getForObject("https://api.cartolafc.globo.com/mercado/status",
                        MercadoResponse.class);

        if(mercado.getStatus_mercado() == 2
                && mercado.getRodada_atual() > 10) {

            AtletasResponse atletas = new RestTemplate()
                    .getForObject("https://api.cartolafc.globo.com/atletas/mercado",
                            AtletasResponse.class);

            atletaRepository.insert(atletas.getAtletas());
        }


        return "";
    }
}
