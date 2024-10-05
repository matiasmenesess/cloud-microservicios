package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
