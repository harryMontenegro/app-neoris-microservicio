package com.neoris.app.serviciomovimiento.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neoris.app.serviciomovimiento.entities.MovimientoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Repository
public interface MovimientoCuentaRepository extends JpaRepository<MovimientoCuenta, Long> {

    @Query(value = "SELECT DATE_FORMAT(m.fechaMovimiento, '%d/%m/%Y') as fecha ,cl.nombres as Cliente,c.numeroCuenta as numeroCuenta,ltc.nombre as tipo,c.saldoInicial  as saldoInicial,c.estado as estado,SUM(m.valor) as movimiento,c.saldoActual as saldoDisponible FROM persona_bd.persona cl inner join cuenta_bd.cuenta c on c.idPersona = cl.idPersona inner join cuenta_bd.listastipocuenta ltc on c.idTipoCuenta = ltc.idLista left join movimiento_bd.movimientocuenta m on m.idCuenta = c.idCuenta inner join movimiento_bd.listastipomovimiento ltm on m.idTipoMovimiento = ltm.idLista WHERE cl.identificacion = :numeroDocumento GROUP BY DATE_FORMAT(m.fechaMovimiento,'%d/%m/%Y'), cliente, numeroCuenta, tipo, saldoInicial, estado, saldoActual HAVING fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY tipo DESC", nativeQuery = true)
    List<EntidadReporte> findAllEntidadReporte(@Param("numeroDocumento") String numeroDocumento, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

    List<MovimientoCuenta> findAllByIdCuentaIn(Set<Long> lista);

    public interface EntidadReporte {
        String getFecha();
        String getCliente();
        String getNumeroCuenta();
        String getTipo();
        BigDecimal getSaldoInicial();
        Integer getEstado();
        BigDecimal getMovimiento();
        BigDecimal getSaldoDisponible();
    }
}