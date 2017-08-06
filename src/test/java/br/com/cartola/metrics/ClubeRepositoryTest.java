package br.com.cartola.metrics;

import br.com.cartola.metrics.model.Clube;
import br.com.cartola.metrics.repository.ClubeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ClubeRepositoryTest {

    @Autowired
    private ClubeRepository clubeRepo;

    @Test
    public void findTest() {
        List<Clube> clubes = clubeRepo.findAll();

        assertEquals(clubes.size(), 20);
    }
}
