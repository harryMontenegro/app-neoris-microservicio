package com.neoris.app.serviciomovimiento.services.mapper;

import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaDTO;
import com.neoris.app.serviciomovimiento.entities.MovimientoCuenta;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
@Component
public interface MovimientoCuentaMapper {
    MovimientoCuentaDTO aDTO(MovimientoCuenta c);
    MovimientoCuenta aEntity(MovimientoCuentaDTO dto);
    default List<MovimientoCuentaDTO> aListaDTO(List<MovimientoCuenta> lista) {
        if (lista == null) return new ArrayList<>();
        return lista.stream().map(MovimientoCuentaDTO::new).collect(Collectors.toList());
    }
}
