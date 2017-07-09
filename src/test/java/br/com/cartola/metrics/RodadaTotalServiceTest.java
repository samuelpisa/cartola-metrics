package br.com.cartola.metrics;

import br.com.cartola.metrics.service.RodadaTotalService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class RodadaTotalServiceTest {

    @Autowired
    private RodadaTotalService rodadaService;

    @Test
    public void testTotalRodada() {

        for (int i = 1; i <= 11; i++) {
            rodadaService.totalizarRodada(i);
        }
    }
}
