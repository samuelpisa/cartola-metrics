package br.com.cartola.metrics.model.cartola;

import br.com.cartola.metrics.model.Atleta;

import java.io.Serializable;
import java.util.List;

/**
 * Created by samuel on 28/06/17.
 */
public class AtletasResponse implements Serializable {

    private List<Atleta> atletas;

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }
}
