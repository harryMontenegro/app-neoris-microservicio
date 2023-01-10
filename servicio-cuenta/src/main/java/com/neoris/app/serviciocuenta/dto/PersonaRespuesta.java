package com.neoris.app.serviciocuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRespuesta {
    private Long idPersona;
    private String identificacion;
    private String nombres;
    private Integer idGenero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private Boolean estado;
}
