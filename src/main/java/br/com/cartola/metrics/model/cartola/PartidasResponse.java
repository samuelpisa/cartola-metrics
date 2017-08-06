package br.com.cartola.metrics.model.cartola;

import br.com.cartola.metrics.model.Partida;

import java.util.List;

public class PartidasResponse {

    private Integer rodada;
    private List<Partida> partidas;

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }
}
