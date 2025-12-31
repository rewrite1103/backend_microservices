package com.unir.ms_buscador.controller;

import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.unir.ms_buscador.entity.libro;
import com.unir.ms_buscador.repository.libroRepository;
import com.unir.ms_buscador.service.libroService;

@RequestMapping("/search")
@RestController
public class libroController {


    private final  libroService libroService;
    private final libroRepository libroRepository;

    public libroController(libroService libroService, libroRepository libroRepository) {
        this.libroService = libroService;
        this.libroRepository = libroRepository;
    }

@PostMapping("/addLibros")
    public ResponseEntity<String> addLibros (@RequestBody List<libro> libros) {
        final var resul = libroRepository.saveAll(libros);
        System.out.println("Libros añadidos: " + resul);
        return ResponseEntity.ok("Libros añadidos correctamente");
    }

@GetMapping("/viewLibros")
public List<libro> viewLibros() {

    return libroRepository.findAll();
}

@GetMapping("/sugerencias")
    public ResponseEntity<List<libro>> sugerirLibro(@RequestParam String texto) {
        List<libro> sugerencias = libroService.sugerirLibro(texto);
        return ResponseEntity.ok(sugerencias);
}

@GetMapping("/facets")
    public ResponseEntity<Map<String, Long>> obtenerFacets() {
        Map<String, Long> facets = libroService.obtenerFacetsPorCategoria();
        

        return ResponseEntity.ok(facets);
    }

}