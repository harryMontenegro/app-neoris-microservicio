package com.neoris.app.serviciocuenta.services.mapper;

import com.neoris.app.serviciocuenta.dto.ListasTipoCuentaDTO;
import com.neoris.app.serviciocuenta.entities.ListasTipoCuenta;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ListasTipoCuentaMapper {
    ListasTipoCuentaDTO aDTO(ListasTipoCuenta c);
    ListasTipoCuenta aEntity(ListasTipoCuentaDTO dto);
    default List<ListasTipoCuentaDTO> aListaDTO(List<ListasTipoCuenta> lista) {
        if (lista == null) return new ArrayList<>();
        return lista.stream().map(ListasTipoCuentaDTO::new).collect(Collectors.toList());
    }
}
