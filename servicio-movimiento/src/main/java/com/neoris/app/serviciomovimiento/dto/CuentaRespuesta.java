package com.neoris.app.serviciomovimiento.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
public class CuentaRespuesta {
    private Long idCuenta;
    private Long idPersona;
    private String numeroCuenta;
    private Integer idTipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado;

    //Transient
    private String nombreTipoCuenta;
}
