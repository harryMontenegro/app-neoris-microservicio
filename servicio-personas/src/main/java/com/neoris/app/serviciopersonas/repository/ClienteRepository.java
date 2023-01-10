package com.neoris.app.serviciopersonas.repository;

import com.neoris.app.serviciopersonas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByPersona_Identificacion(String identificacion);
    Optional<Cliente> findByIdPersona(Long idPersona);
}
