package com.neoris.app.serviciomovimiento.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "movimientocuenta", catalog = "movimiento_bd")
@Inheritance(strategy = InheritanceType.JOINED)
public class MovimientoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimientoCuenta;
    private Long idCuenta;
    private LocalDateTime fechaMovimiento;
    private Integer idTipoMovimiento;
    private BigDecimal valor;
    private BigDecimal Saldo;
    private Boolean estado;
    private String movimiento;
}
