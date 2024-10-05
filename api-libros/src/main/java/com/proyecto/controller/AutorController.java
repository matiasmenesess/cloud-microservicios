package com.proyecto.controller;

import com.proyecto.model.Autor;
import com.proyecto.repository.AutorRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Operation(summary = "Obtener todos los autores", description = "Retorna una lista de todos los autores registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }

    @Operation(summary = "Crear un nuevo autor", description = "Agrega un nuevo autor al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    @PostMapping
    public Autor createAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @Operation(summary = "Obtener un autor por ID", description = "Busca y devuelve un autor basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un autor por ID", description = "Elimina un autor basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
