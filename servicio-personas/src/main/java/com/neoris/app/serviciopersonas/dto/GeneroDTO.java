package com.neoris.app.serviciopersonas.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneroDTO {
    private Integer idGenero;
    private String codigo;
    private String nombre;
    private Boolean estado;
}
