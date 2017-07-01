package br.com.cartola.metrics.model;

import java.util.Date;

public class Partida {

    private Integer clube_casa_id;
    private Integer clube_visitante_id;
    private Boolean valida;

    public Integer getClube_casa_id() {
        return clube_casa_id;
    }

    public void setClube_casa_id(Integer clube_casa_id) {
        this.clube_casa_id = clube_casa_id;
    }

    public Integer getClube_visitante_id() {
        return clube_visitante_id;
    }

    public void setClube_visitante_id(Integer clube_visitante_id) {
        this.clube_visitante_id = clube_visitante_id;
    }

    public Boolean getValida() {
        return valida;
    }

    public void setValida(Boolean valida) {
        this.valida = valida;
    }
}
