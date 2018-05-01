package br.com.cartola.metrics.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "analise_atleta")
class AnaliseAtleta {
    @Id
    var rodada_id: Int? = null
    var atletas: MutableList<Atleta> = mutableListOf()
}
