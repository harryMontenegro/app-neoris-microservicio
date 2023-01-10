package com.neoris.app.serviciopersonas.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "genero", catalog = "persona_bd")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenero;
    private String codigo;
    private String nombre;
    private Boolean estado;
}
