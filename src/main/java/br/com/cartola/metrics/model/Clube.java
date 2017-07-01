package br.com.cartola.metrics.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by samuel on 10/06/17.
 */
@Document(collection = "clubes")
public class Clube {

    @Id
    private Integer id;
    private String nome;
    private String abreviacao;
    private Escudo escudos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public Escudo getEscudos() {
        return escudos;
    }

    public void setEscudos(Escudo escudos) {
        this.escudos = escudos;
    }
}
