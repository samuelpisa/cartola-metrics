package br.com.cartola.metrics.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by samuel on 10/06/17.
 */
@Document(collection = "clubes")
class Clube {

    @Id
    var id: Int? = null
    var nome: String? = null
    var abreviacao: String? = null
    var escudos: Escudo? = null
    var pontos: PontoClube? = null
    var mandante: PontoClube? = null
    var visitante: PontoClube? = null
    var rodadas: MutableList<RodadaClube> = mutableListOf()
}

class RodadaClube {
    var id: Int? = null
    var pontos: Double = 0.0
    var pontosCedidos: Double = 0.0
    var casa: Boolean = true
    var valida: Boolean = true
}

class Escudo {
    var pequeno: String? = null
    var medio: String? = null
    var grande: String? = null
}

class PontoClube(
        var mediaPontos: Double,
        var totalPontos: Double,
        var mediaCedidos: Double,
        var totalCedidos: Double
)
