package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
