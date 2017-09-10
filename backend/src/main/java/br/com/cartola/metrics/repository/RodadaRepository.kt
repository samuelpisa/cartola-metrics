package br.com.cartola.metrics.repository

import br.com.cartola.metrics.model.Rodada
import org.springframework.data.mongodb.repository.MongoRepository

interface RodadaRepository : MongoRepository<Rodada, Int>
