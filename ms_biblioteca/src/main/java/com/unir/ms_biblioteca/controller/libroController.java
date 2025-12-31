package com.unir.ms_biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.ms_biblioteca.repository.libroRepository;
import com.unir.ms_biblioteca.model.libro;
import com.unir.ms_biblioteca.model.alquiler;
import com.unir.ms_biblioteca.repository.alquilerRepository;


@RequestMapping("/libros")
@RestController
public class libroController {

    private final libroRepository libroRepository;
    private final alquilerRepository alquilerRepository;

    public libroController(libroRepository libroRepository, alquilerRepository alquilerRepository) {
        this.libroRepository = libroRepository;
        this.alquilerRepository = alquilerRepository;
    }

    @GetMapping("/list")
    public List<libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/list_alquiler")
    public List<alquiler> getAllAlquiler() {
        return alquilerRepository.findAll();
    }

    @PostMapping("/addalquiler")
    public ResponseEntity<String> AddAlquiler(@RequestBody alquiler alquiler) {
        System.out.println("Recibiendo alquiler: " + alquiler);
        alquilerRepository.save(alquiler);
        return ResponseEntity.ok("Alquiler agregado correctamente");
    }

    @PutMapping("/update_alquiler")
    public ResponseEntity<String> ActualizarAlquiler(@RequestBody alquiler alquiler) {
        alquilerRepository.save(alquiler);
        return ResponseEntity.ok("Alquiler actualizado correctamente");
    }

    @PostMapping("/addlibro")
    public ResponseEntity<String> AddLibro(@RequestBody libro libro) {
        libroRepository.save(libro);
        return ResponseEntity.ok("Libro agregado correctamente");
    }

    @PostMapping("/addlibros")
    public ResponseEntity<String> AddLibros(@RequestBody List<libro> libros) {
        for (libro libro : libros) {
            libroRepository.save(libro);
            System.out.println("Agregando libro: " + libro.getTitulo());
        }
        
        return ResponseEntity.ok("Libros agregados correctamente");
    }

}
