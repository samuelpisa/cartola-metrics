package br.com.cartola.metrics.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by samuel on 30/05/17.
 */
@Document(collection = "atletas")
public class Atleta {

    @Id
    private Integer atleta_id;
    private String nome;
    private String apelido;
    private String foto;
    private Integer rodada_id;
    private Double pontos_num;
    private Integer clube_id;
    private Integer posicao_id;
    private Integer status_id;
    private Double preco_num;
    private Double variacao_num;
    private Double media_num;
    private Integer jogos_num;
    private Scout scout;

    public Scout getScout() { return scout; }

    public void setScout(Scout scout) { this.scout = scout; }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getClube_id() {
        return clube_id;
    }

    public void setClube_id(Integer clube_id) {
        this.clube_id = clube_id;
    }

    public Integer getPosicao_id() {
        return posicao_id;
    }

    public void setPosicao_id(Integer posicao_id) {
        this.posicao_id = posicao_id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Double getPreco_num() {
        return preco_num;
    }

    public void setPreco_num(Double preco_num) {
        this.preco_num = preco_num;
    }

    public Double getVariacao_num() {
        return variacao_num;
    }

    public void setVariacao_num(Double variacao_num) {
        this.variacao_num = variacao_num;
    }

    public Double getMedia_num() {
        return media_num;
    }

    public void setMedia_num(Double media_num) {
        this.media_num = media_num;
    }

    public Integer getJogos_num() {
        return jogos_num;
    }

    public void setJogos_num(Integer jogos_num) {
        this.jogos_num = jogos_num;
    }

    public Integer getAtleta_id() {
        return atleta_id;
    }

    public void setAtleta_id(Integer atleta_id) {
        this.atleta_id = atleta_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getRodada_id() {
        return rodada_id;
    }

    public void setRodada_id(Integer rodada_id) {
        this.rodada_id = rodada_id;
    }

    public Double getPontos_num() {
        return pontos_num;
    }

    public void setPontos_num(Double pontos_num) {
        this.pontos_num = pontos_num;
    }
}
