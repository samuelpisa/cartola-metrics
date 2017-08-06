package br.com.cartola.metrics.model;

public class PontoClube {

    private Double mediaPontos;
    private Double totalPontos;
    private Double mediaCedidos;
    private Double totalCedidos;

    public PontoClube(Double mediaPontos, Double totalPontos, Double mediaCedidos, Double totalCedidos) {
        this.mediaPontos = mediaPontos;
        this.totalPontos = totalPontos;
        this.mediaCedidos = mediaCedidos;
        this.totalCedidos = totalCedidos;
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
