package com.neoris.app.serviciomovimiento.dto;


import com.neoris.app.serviciomovimiento.entities.MovimientoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoCuentaDTO {
    private Long idMovimientoCuenta;
    private Long idCuenta;
    private LocalDateTime fechaMovimiento;
    private Integer idTipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private String movimiento;
    private Boolean estado;

    public MovimientoCuentaDTO(MovimientoCuenta m) {
        this.idMovimientoCuenta = m.getIdMovimientoCuenta();
        this.idCuenta = m.getIdCuenta();
        this.fechaMovimiento = m.getFechaMovimiento();
        this.idTipoMovimiento = m.getIdTipoMovimiento();
        this.valor = m.getValor();
        this.saldo = m.getSaldo();
        this.estado = m.getEstado();
        this.movimiento = m.getMovimiento();
    }

    public MovimientoCuentaDTO(CuentaRespuesta c, Integer idTipoMovimiento, BigDecimal valor, String descripcion) {
        this.idCuenta = c.getIdCuenta();
        this.fechaMovimiento = LocalDateTime.now();
        this.idTipoMovimiento = idTipoMovimiento;
        this.valor = valor;
        this.saldo = c.getSaldoActual();
        this.movimiento = descripcion;
    }
}
