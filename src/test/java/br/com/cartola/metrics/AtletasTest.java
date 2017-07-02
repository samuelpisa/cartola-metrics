package br.com.cartola.metrics;


import br.com.cartola.metrics.model.Atleta;
import br.com.cartola.metrics.model.Rodada;
import br.com.cartola.metrics.model.cartola.AtletasResponse;
import br.com.cartola.metrics.model.cartola.PartidasResponse;
import br.com.cartola.metrics.repository.RodadaRepository;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class AtletasTest {

    @Autowired
    private RodadaRepository rodadaRepo;

    @Test
    public void insertRodada() throws IOException {
        Path dir = Paths.get("src", "main", "resources", "atletas");

        for (int i = 2; i <= 10; i++) {


            File arquivo = new File(dir.toString() + "/atletas-rodada-" + i + ".json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            AtletasResponse atletaResponse = mapper.readValue(arquivo, AtletasResponse.class);

            PartidasResponse partidaResponse = new RestTemplate()
                    .getForObject("https://api.cartolafc.globo.com/partidas/" + i,
                            PartidasResponse.class);

            Rodada rodada = new Rodada();
            rodada.setAtletas(atletaResponse.getAtletas());
            rodada.setPartidas(partidaResponse.getPartidas());
            rodada.setId(i);
            rodadaRepo.save(rodada);
        }
    }

    @Test
    public void insertPrimeiraRodada() throws IOException {
        Path dir = Paths.get("src", "main", "resources", "atletas");


        File arquivo = new File(dir.toString() + "/atletas-rodada-1.json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        AtletasResponse atletaResponse = mapper.readValue(arquivo, AtletasResponse.class);

        PartidasResponse partidaResponse = new RestTemplate()
                .getForObject("https://api.cartolafc.globo.com/partidas/1",
                        PartidasResponse.class);

        for (Atleta a : atletaResponse.getAtletas()) {
            a.setPontos_num(a.getMedia_num());
        }

        Rodada rodada = new Rodada();
        rodada.setAtletas(atletaResponse.getAtletas());
        rodada.setPartidas(partidaResponse.getPartidas());
        rodada.setId(1);
        rodadaRepo.save(rodada);

    }
}
