package com.unir.ms_biblioteca.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unir.ms_biblioteca.model.alquiler;

@Repository
public interface alquilerRepository extends JpaRepository<alquiler, Long> {

}
