package com.neoris.app.serviciocuenta.services.mapper;

import com.neoris.app.serviciocuenta.dto.CuentaDTO;
import com.neoris.app.serviciocuenta.entities.Cuenta;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaDTO aDTO(Cuenta c);
    Cuenta aEntity(CuentaDTO dto);
    default List<CuentaDTO> aListaDTO(List<Cuenta> lista) {
        if (lista == null) return new ArrayList<>();
        return lista.stream().map(CuentaDTO::new).collect(Collectors.toList());
    }
}
