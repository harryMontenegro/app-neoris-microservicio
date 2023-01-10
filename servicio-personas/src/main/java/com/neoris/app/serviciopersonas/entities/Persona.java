package com.neoris.app.serviciopersonas.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
@Table(name = "persona", catalog = "persona_bd")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    private String identificacion;
    private String nombres;
    private Integer idGenero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private Boolean estado;
}
