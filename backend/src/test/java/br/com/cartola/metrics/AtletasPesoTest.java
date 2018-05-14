package br.com.cartola.metrics;

import br.com.cartola.metrics.model.Atleta;
import br.com.cartola.metrics.model.Rodada;
import br.com.cartola.metrics.repository.RodadaRepository;
import br.com.cartola.metrics.service.AtletasService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class AtletasPesoTest {

    @Autowired
    private AtletasService atletasService;
    @Autowired
    private RodadaRepository rodadaRepo;

    @Test
    public void gerarPesoAtletas(){
        Rodada rodada = rodadaRepo.findOne(3);
        List<Atleta> atletas = rodada.getAtletas();

        atletasService.gerarAtletas(atletas, 4);
    }
}
