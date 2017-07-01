package br.com.cartola.metrics.repository;

import br.com.cartola.metrics.model.Clube;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubeRepository extends MongoRepository<Clube, Integer>{
}
