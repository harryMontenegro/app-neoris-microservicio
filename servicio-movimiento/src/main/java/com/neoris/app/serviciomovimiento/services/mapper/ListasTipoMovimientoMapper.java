package com.neoris.app.serviciomovimiento.services.mapper;

import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import com.neoris.app.serviciomovimiento.entities.ListasTipoMovimiento;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
@Component
public interface ListasTipoMovimientoMapper {
    ListasTipoMovimiento aEntity(ListasTipoMovimientoDTO c);
    ListasTipoMovimientoDTO aDTO(ListasTipoMovimiento dto);
    default List<ListasTipoMovimientoDTO> aListaDTO(List<ListasTipoMovimiento> lista) {
        if (lista == null) return new ArrayList<>();
        return lista.stream().map(ListasTipoMovimientoDTO::new).collect(Collectors.toList());
    }
}
