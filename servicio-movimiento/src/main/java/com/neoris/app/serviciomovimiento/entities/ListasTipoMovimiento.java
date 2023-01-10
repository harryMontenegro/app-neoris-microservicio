package com.neoris.app.serviciomovimiento.entities;

import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "listastipomovimiento", catalog = "movimiento_bd")
public class ListasTipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLista;
    private String codigo;
    private String nombre;
    private Boolean estado;

    public ListasTipoMovimiento(ListasTipoMovimiento t) {
        this.idLista = t.getIdLista();
        this.codigo = t.getCodigo();
        this.nombre = t.getNombre();
        this.estado = t.getEstado();
    }

}
