package com.neoris.app.serviciocuenta.dto;

import com.neoris.app.serviciocuenta.entities.ListasTipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ListasTipoCuentaDTO {
    private Integer idLista;
    private String codigo;
    private String nombre;
    private Boolean estado;

    public ListasTipoCuentaDTO(ListasTipoCuenta t) {
        this.idLista = t.getIdLista();
        this.codigo = t.getCodigo();
        this.nombre = t.getNombre();
        this.estado = t.getEstado();
    }
}
