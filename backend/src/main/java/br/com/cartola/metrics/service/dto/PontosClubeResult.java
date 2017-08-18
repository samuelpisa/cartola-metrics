package br.com.cartola.metrics.service.dto;

public class PontosClubeResult {

    private Integer id;
    private Double mediaPontos;
    private Double totalPontos;
    private Double mediaCedidos;
    private Double totalCedidos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMediaPontos() {
        return mediaPontos;
    }

    public void setMediaPontos(Double mediaPontos) {
        this.mediaPontos = mediaPontos;
    }

    public Double getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(Double totalPontos) {
        this.totalPontos = totalPontos;
    }

    public Double getMediaCedidos() {
        return mediaCedidos;
    }

    public void setMediaCedidos(Double mediaCedidos) {
        this.mediaCedidos = mediaCedidos;
    }

    public Double getTotalCedidos() {
        return totalCedidos;
    }

    public void setTotalCedidos(Double totalCedidos) {
        this.totalCedidos = totalCedidos;
    }
}
