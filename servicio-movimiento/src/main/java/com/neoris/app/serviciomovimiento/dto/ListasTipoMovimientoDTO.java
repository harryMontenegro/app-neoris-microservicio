package com.neoris.app.serviciomovimiento.dto;

import com.neoris.app.serviciomovimiento.entities.ListasTipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ListasTipoMovimientoDTO {
    private Integer idLista;
    private String codigo;
    private String nombre;
    private Boolean estado;

    public ListasTipoMovimientoDTO(ListasTipoMovimiento t) {
        this.idLista = t.getIdLista();
        this.codigo = t.getCodigo();
        this.nombre = t.getNombre();
        this.estado = t.getEstado();
    }
}
