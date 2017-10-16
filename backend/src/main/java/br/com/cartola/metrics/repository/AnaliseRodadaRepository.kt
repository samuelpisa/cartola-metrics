package br.com.cartola.metrics.repository

import br.com.cartola.metrics.model.AnaliseRodada
import org.springframework.data.mongodb.repository.MongoRepository

interface AnaliseRodadaRepository : MongoRepository<AnaliseRodada, Int>