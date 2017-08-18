package br.com.cartola.metrics.repository;

import br.com.cartola.metrics.model.Rodada;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RodadaRepository extends MongoRepository<Rodada, Integer> {

}
