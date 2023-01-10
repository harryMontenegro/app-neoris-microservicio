package com.neoris.app.serviciocuenta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "ListasTipoCuenta", catalog = "cuenta_bd")
public class ListasTipoCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLista;
    private String codigo;
    private String nombre;
    private Boolean estado;
}
