package br.com.cartola.metrics.repository

import br.com.cartola.metrics.model.Clube
import org.springframework.data.mongodb.repository.MongoRepository

interface ClubeRepository : MongoRepository<Clube, Int>
