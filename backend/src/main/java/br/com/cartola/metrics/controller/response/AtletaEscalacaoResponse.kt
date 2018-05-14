package br.com.cartola.metrics.controller.response

import br.com.cartola.metrics.model.Atleta
import br.com.cartola.metrics.model.Posicao
import br.com.cartola.metrics.model.Status

class AtletaEscalacaoResponse(
        var atleta_id: Int? = null,
        var apelido: String? = null,
        var foto: String? = null,
        var clube_id: Int? = null,
        var posicao_id: String? = null,
        var status_id: String? = null,
        var preco_num: Double? = null,
        var media_num: Double? = null,
        var jogos_num: Int? = null
) {
    companion object {
        fun from(a: Atleta) = AtletaEscalacaoResponse(
                atleta_id = a.atleta_id,
                apelido = a.apelido,
                foto = a.foto,
                clube_id = a.clube_id,
                posicao_id = Posicao.values().first { it.id == a.posicao_id }.name,
                status_id = Status.values().first { it.id == a.status_id }.name,
                preco_num = a.preco_num,
                media_num = a.media_num,
                jogos_num = a.jogos_num
        )
    }
}