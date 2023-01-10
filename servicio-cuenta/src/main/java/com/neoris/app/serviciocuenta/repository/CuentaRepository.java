package com.neoris.app.serviciocuenta.repository;

import com.neoris.app.serviciocuenta.dto.CuentaDTO;
import com.neoris.app.serviciocuenta.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findCuentaByIdPersonaAndNumeroCuenta(Long idPersona, String numeroCuenta);
    Optional<List<Cuenta>> findAllByIdPersona(Long idPersona);

    Optional<CuentaDTO> findByNumeroCuenta(String numeroCuenta);
}
