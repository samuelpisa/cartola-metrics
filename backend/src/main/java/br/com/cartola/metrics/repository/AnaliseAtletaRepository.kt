package br.com.cartola.metrics.repository

import br.com.cartola.metrics.model.AnaliseAtleta
import org.springframework.data.mongodb.repository.MongoRepository

interface AnaliseAtletaRepository : MongoRepository<AnaliseAtleta, Int>