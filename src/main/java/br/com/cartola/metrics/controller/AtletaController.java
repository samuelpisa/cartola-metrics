package br.com.cartola.metrics.controller;

import br.com.cartola.metrics.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by samuel on 30/05/17.
 */
@RestController
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping(value = "/")
    private String index() {
        return "cartola top";
    }
}
