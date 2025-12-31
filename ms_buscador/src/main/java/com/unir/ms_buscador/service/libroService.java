package com.unir.ms_buscador.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;



import com.unir.ms_buscador.entity.libro;





@Service
public class libroService {

    private final ElasticsearchOperations elastic;

    public libroService(ElasticsearchOperations elastic) {
        this.elastic = elastic;
    }

    public List<libro> sugerirLibro(String texto) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .match(m -> m
                                .field("titulo")
                                .query(texto)
                                .fuzziness("1")))
                .build();

        return elastic.search(query, libro.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

    public Map<String, Long> obtenerFacetsPorCategoria() {

        NativeQuery query = NativeQuery.builder()
                .withAggregation("categorias_facet", Aggregation.of(a -> a
                        .terms(t -> t.field("categoria"))))
                .withMaxResults(0)
                .build();

        SearchHits<libro> searchHits = elastic.search(query, libro.class);


        if (searchHits.getAggregations() == null) {
            return Map.of();
        }


        ElasticsearchAggregations esAggs = (ElasticsearchAggregations) searchHits.getAggregations();

        return esAggs.aggregations().stream()
                .filter(agg -> "categorias_facet".equals(agg.aggregation().getName()))
                .findFirst()
                .map(agg -> {
                    Aggregate aggregate = agg.aggregation().getAggregate();


                    if (aggregate.isSterms()) {
                        return aggregate.sterms().buckets().array().stream()
                                .collect(Collectors.toMap(
                                        bucket -> bucket.key().stringValue(),
                                        bucket -> bucket.docCount()));
                    }
                    return Map.<String, Long>of();
                }).orElse(Map.of());
    }


}