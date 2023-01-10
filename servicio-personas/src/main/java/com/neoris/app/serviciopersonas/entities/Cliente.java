package com.neoris.app.serviciopersonas.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
@PrimaryKeyJoinColumn(name = "idPersona")
@Table(name = "cliente", catalog = "persona_bd")
public class Cliente extends Persona{

    private Long idCliente;
    private Long idPersona;
    private String contrasenia;
    private Boolean estado;

    @OneToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private Persona persona;
}
