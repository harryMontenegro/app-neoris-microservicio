package com.neoris.app.serviciomovimiento.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class PeticionTransaccion {

    @NotBlank(message = "NumeroCuenta no debe estar vacío")
    private String numeroCuenta;

    @Positive(message = "El valor de la transacción debe ser mayor a cero")
    @DecimalMin(message = "valor debe ser mayor a 5", value = "5")
    @NotNull(message = "Valor de transacción no puede ser null")
    private BigDecimal valorTransacion;

    @NotBlank(message = "Nombres no debe estar vacío")
    @NotNull(message = "Nombres no debe ser null")
    private String codigoTipoTransaccion;
}
