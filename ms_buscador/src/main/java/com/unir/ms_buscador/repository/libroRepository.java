package com.unir.ms_buscador.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.unir.ms_buscador.entity.libro;

@Repository
public interface libroRepository extends ElasticsearchRepository<libro, String> {

    @Autowired
    List<libro> findAll();

    @Query("{\"match\": {\"titulo\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<libro> findByTituloFuzzy(String titulo);


}
