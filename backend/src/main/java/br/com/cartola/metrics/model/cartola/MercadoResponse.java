package br.com.cartola.metrics.model.cartola;

import java.io.Serializable;

/**
 * Created by samuel on 28/06/17.
 */
public class MercadoResponse implements Serializable {

    private Long rodada_atual;
    private Long status_mercado;
    private Long temporada;

    public Long getRodada_atual() {
        return rodada_atual;
    }

    public void setRodada_atual(Long rodada_atual) {
        this.rodada_atual = rodada_atual;
    }

    public Long getStatus_mercado() {
        return status_mercado;
    }

    public void setStatus_mercado(Long status_mercado) {
        this.status_mercado = status_mercado;
    }

    public Long getTemporada() {
        return temporada;
    }

    public void setTemporada(Long temporada) {
        this.temporada = temporada;
    }
}
