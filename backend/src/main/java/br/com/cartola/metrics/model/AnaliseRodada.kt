package br.com.cartola.metrics.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "analise")
class AnaliseRodada {
    @Id
    var rodada_id: Int? = null
    var partidas: MutableList<AnalisePartida> = mutableListOf()
}

class AnalisePartida {
    var clubeCasa: Clube? = null
    var clubeVisitante: Clube? = null
    var data: Date? = null
    var local: String? = null
    var valida: Boolean = true
    var seloCasa: Boolean = true
    var diferencaPontos: Double? = null
}
