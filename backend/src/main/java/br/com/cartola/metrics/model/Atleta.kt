package br.com.cartola.metrics.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by samuel on 30/05/17.
 */
@Document(collection = "atletas")
class Atleta {

    @Id
    var atleta_id: Int? = null
    var nome: String? = null
    var apelido: String? = null
    var foto: String? = null
    var rodada_id: Int? = null
    var pontos_num: Double? = null
    var clube_id: Int? = null
    var posicao_id: Int? = null
    var status_id: Int? = null
    var preco_num: Double? = null
    var variacao_num: Double? = null
    var media_num: Double? = null
    var jogos_num: Int? = null
    var scout: Scout? = null
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Scout {
    var rb: Int? = null
    var g: Int? = null
    var a: Int? = null
    var sg: Int? = null
    var fs: Int? = null
    var ff: Int? = null
    var fd: Int? = null
    var ft: Int? = null
    var dd: Int? = null
    var dp: Int? = null
    var gc: Int? = null
    var cv: Int? = null
    var ca: Int? = null
    var gs: Int? = null
    var pp: Int? = null
    var fc: Int? = null
    var i: Int? = null
    var pe: Int? = null
}
