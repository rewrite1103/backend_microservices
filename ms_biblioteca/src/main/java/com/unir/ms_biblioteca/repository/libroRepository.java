package com.unir.ms_biblioteca.repository;

import com.unir.ms_biblioteca.model.libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface libroRepository extends JpaRepository<libro, Long> {

    
    
}
