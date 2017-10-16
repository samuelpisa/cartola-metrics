package br.com.cartola.metrics.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import com.fasterxml.jackson.annotation.JsonFormat



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
    var local: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var partida_data: Date? = null
}