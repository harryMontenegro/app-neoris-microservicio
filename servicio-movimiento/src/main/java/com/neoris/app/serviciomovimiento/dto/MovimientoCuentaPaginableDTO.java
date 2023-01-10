package com.neoris.app.serviciomovimiento.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovimientoCuentaPaginableDTO {
    private Integer pagina;
    private Integer cantidad;
    private LocalDate fechaConsulta;
    private String numeroDocumento;
}
