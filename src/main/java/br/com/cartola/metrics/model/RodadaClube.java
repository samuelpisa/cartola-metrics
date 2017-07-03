package br.com.cartola.metrics.model;

public class RodadaClube {

    private Integer id;
    private Double pontos;
    private Boolean casa;
    private Boolean valida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public Boolean getCasa() {
        return casa;
    }

    public void setCasa(Boolean casa) {
        this.casa = casa;
    }

    public Boolean getValida() {
        return valida;
    }

    public void setValida(Boolean valida) {
        this.valida = valida;
    }
}
