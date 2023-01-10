package com.neoris.app.serviciopersonas.dto;


import com.neoris.app.serviciopersonas.entities.Cliente;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClienteDTO extends PersonaDTO {
    private Long idCliente;
    @NotBlank(message = "contraseña no debe estar vacío")
    @Size(min = 4, max = 8, message = "contraseña debe tener mínimo 4 y máximo 8 dígitos")
    @NotNull(message = "contraseña no debe ser null")
    private String contrasenia;
    private Boolean estado;


    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.contrasenia = cliente.getContrasenia();
        this.estado = cliente.getEstado();
    }


    public ClienteDTO(String identificacion, String nombres, Integer idGenero, Integer edad, String direccion,
                      String telefono, Boolean estado, String contrasenia, Boolean estado1) {

        super(identificacion, nombres, idGenero, edad, direccion, telefono, estado);
        this.contrasenia = contrasenia;
        this.estado = estado1;
    }
}
