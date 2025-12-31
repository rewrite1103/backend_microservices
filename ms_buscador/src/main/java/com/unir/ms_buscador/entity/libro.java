package com.unir.ms_buscador.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "libros")
public class libro {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "spanish") 
    private String titulo;

    @Field(type = FieldType.Keyword) 
    private String categoria;

    @Field(type = FieldType.Keyword)
    private String autor;
}