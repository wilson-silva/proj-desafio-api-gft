package com.gft.sistema_programa_starter.repositories;

import com.gft.sistema_programa_starter.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findAll(Pageable pageable);

}
