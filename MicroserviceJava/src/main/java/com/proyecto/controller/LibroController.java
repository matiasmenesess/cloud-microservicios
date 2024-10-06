package com.proyecto.controller;

import com.proyecto.model.Libro;
import com.proyecto.repository.LibroRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @Operation(summary = "Obtener todos los libros", description = "Retorna una lista de todos los libros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Operation(summary = "Crear un nuevo libro", description = "Agrega un nuevo libro al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Libro creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @Operation(summary = "Obtener un libro por ID", description = "Busca y devuelve un libro basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un libro por ID", description = "Elimina un libro basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Libro eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
