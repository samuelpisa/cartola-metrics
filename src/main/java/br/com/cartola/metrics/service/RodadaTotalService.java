package br.com.cartola.metrics.service;

import br.com.cartola.metrics.model.Rodada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class RodadaTotalService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public RodadaTotalService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void totalizarRodada(Integer rodadaId) {
        Aggregation agg = newAggregation(
                match(where("_id").is(rodadaId)),
                project("atletas"),
                unwind("atletas"),
                group("atletas.clube_id").sum("atletas.pontos_num").as("total")
        );

        AggregationResults<RodadaTotalResults> groupResults = mongoTemplate.aggregate(agg, Rodada.class, RodadaTotalResults.class);
        List<RodadaTotalResults> results = groupResults.getMappedResults();
    }
}
