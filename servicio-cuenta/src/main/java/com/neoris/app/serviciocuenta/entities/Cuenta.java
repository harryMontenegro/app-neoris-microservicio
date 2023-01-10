package com.neoris.app.serviciocuenta.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "cuenta", catalog = "cuenta_bd")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;
    private Long idPersona;
    private String numeroCuenta;
    private Integer idTipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idTipoCuenta", referencedColumnName = "idLista", insertable =  false, updatable = false)
    private ListasTipoCuenta listasTipoCuenta;

    @Transient
    private String nombreTipoCuenta;

    public String getNombreTipoCuenta() {
        return listasTipoCuenta != null ? listasTipoCuenta.getNombre() : "";
    }
}
