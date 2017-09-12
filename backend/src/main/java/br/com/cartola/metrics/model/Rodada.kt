package br.com.cartola.metrics.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rodadas")
class Rodada {

    @Id
    var id: Int? = null
    var atletas: List<Atleta> = mutableListOf()
    var partidas: List<Partida> = mutableListOf()
}


class Partida {
    var clube_casa_id: Int? = null
    var clube_visitante_id: Int? = null
    var valida: Boolean = true
}