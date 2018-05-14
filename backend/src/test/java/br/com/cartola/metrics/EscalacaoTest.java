package br.com.cartola.metrics;

import br.com.cartola.metrics.model.Atleta;
import br.com.cartola.metrics.model.cartola.AtletasResponse;
import br.com.cartola.metrics.model.cartola.EscaladosResponse;
import br.com.cartola.metrics.service.EscalacaoService;
import br.com.cartola.metrics.service.RemoteCartolaService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class EscalacaoTest {

    @Autowired
    private EscalacaoService escalacaoService;

    @Autowired
    private RemoteCartolaService remote;

    @Test
    public void escalacaoTest() {
        List<List<Atleta>> escalacoes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            escalacoes.add(escalacaoService.escalar(50.0));
        }


        Assert.assertEquals(10, escalacoes.size());
    }

    @Test
    public void escalados(){
        List<EscaladosResponse> resp = remote.callMaisEscalados();

        Assert.assertEquals(20, 20);
    }

}
