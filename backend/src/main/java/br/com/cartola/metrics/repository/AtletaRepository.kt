package br.com.cartola.metrics.repository

import br.com.cartola.metrics.model.Atleta
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by samuel on 30/05/17.
 */
interface AtletaRepository : MongoRepository<Atleta, Int>
