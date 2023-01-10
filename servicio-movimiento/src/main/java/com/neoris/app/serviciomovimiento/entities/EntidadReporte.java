package com.neoris.app.serviciomovimiento.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntidadReporte {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Integer estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
