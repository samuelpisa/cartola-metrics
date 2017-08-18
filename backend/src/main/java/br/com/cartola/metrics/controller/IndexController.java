package br.com.cartola.metrics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {


    @GetMapping(value = "/")
    @ResponseBody
    private ModelAndView index(){
        return new ModelAndView("index.html");
    }
}
