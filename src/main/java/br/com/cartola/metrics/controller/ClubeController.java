package br.com.cartola.metrics.controller;

import br.com.cartola.metrics.model.Clube;
import br.com.cartola.metrics.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by samuel on 30/05/17.
 */
@RestController
public class ClubeController {

    @Autowired
    private ClubeRepository clubeRepo;

    @GetMapping(value = "/clubes")
    @ResponseBody
    private List<Clube> clubes() {
        return clubeRepo.findAll(new Sort(Sort.Direction.DESC, "pontos.mediaPontos"));
    }
}
