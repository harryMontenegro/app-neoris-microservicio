package com.neoris.app.serviciopersonas.repository;

import com.neoris.app.serviciopersonas.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}
