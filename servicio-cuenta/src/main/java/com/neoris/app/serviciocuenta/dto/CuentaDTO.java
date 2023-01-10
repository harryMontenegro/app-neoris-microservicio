package com.neoris.app.serviciocuenta.dto;

import com.neoris.app.serviciocuenta.entities.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {
    private Long idCuenta;
    @NotNull(message = "Id de persona es requerido para crear la cuenta")
    private Long idPersona;
    private String numeroCuenta;

    @NotNull(message = "IdTipoCuenta no puede ser null")
    private Integer idTipoCuenta;
    @NotNull(message = "Saldo no puede ser vacío")
    @Positive(message = "Saldo inicial debe ser un valor positivo")
    private BigDecimal saldoInicial;
    private Boolean estado;

    private BigDecimal saldoActual;

    //Transient
    private String nombreTipoCuenta;

    public CuentaDTO(Cuenta cuenta) {
        this.idCuenta = cuenta.getIdCuenta();
        this.idPersona = cuenta.getIdPersona();
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.idTipoCuenta = cuenta.getIdTipoCuenta();
        this.saldoInicial = cuenta.getSaldoInicial();
        this.estado = cuenta.getEstado();
        this.saldoActual = cuenta.getSaldoActual();
        this.nombreTipoCuenta = cuenta.getNombreTipoCuenta();
    }
}
