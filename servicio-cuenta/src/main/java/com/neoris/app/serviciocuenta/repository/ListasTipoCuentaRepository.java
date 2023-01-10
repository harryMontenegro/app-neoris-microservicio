package com.neoris.app.serviciocuenta.repository;

import com.neoris.app.serviciocuenta.entities.ListasTipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListasTipoCuentaRepository extends JpaRepository<ListasTipoCuenta, Integer> {
}
