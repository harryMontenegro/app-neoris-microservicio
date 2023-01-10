package com.neoris.app.serviciopersonas.dto;

import com.neoris.app.serviciopersonas.entities.Persona;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonaDTO {
    private Long idPersona;
    @NotBlank(message = "Identificación no debe estar vacío")
    @NotNull(message = "Identificación no debe ser null")
    private String identificacion;

    @NotBlank(message = "Nombres no debe estar vacío")
    @NotNull(message = "Nombres no debe ser null")
    private String nombres;
    @NotNull(message = "Genero no debe ser null")
    private Integer idGenero;
    private Integer edad;
    private String direccion;

    @NotBlank(message = "Telefono no debe estar vacío")
    @NotNull(message = "Telefono no debe ser null")
    private String telefono;
    private Boolean estado;

    public PersonaDTO(Persona persona) {
        this.idPersona = persona.getIdPersona();
        this.identificacion = persona.getIdentificacion();
        this.nombres = persona.getNombres();
        this.idGenero = persona.getIdGenero();
        this.edad = persona.getEdad();
        this.direccion = persona.getDireccion();
        this.telefono = persona.getTelefono();
        this.estado = persona.getEstado();
    }

    public PersonaDTO(Page<Persona> personas) {
    }

    public PersonaDTO(String identificacion, String nombres, Integer idGenero, Integer edad, String direccion, String telefono, Boolean estado) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.idGenero = idGenero;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = direccion;
        this.estado = estado;
    }
}
