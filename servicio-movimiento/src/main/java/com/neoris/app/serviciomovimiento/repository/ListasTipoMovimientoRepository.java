package com.neoris.app.serviciomovimiento.repository;

import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import com.neoris.app.serviciomovimiento.entities.ListasTipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListasTipoMovimientoRepository extends JpaRepository<ListasTipoMovimiento, Integer> {
    ListasTipoMovimientoDTO findByCodigo(String codigo);

}
