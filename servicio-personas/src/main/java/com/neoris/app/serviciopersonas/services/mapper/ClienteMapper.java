package com.neoris.app.serviciopersonas.services.mapper;

import com.neoris.app.serviciopersonas.dto.ClienteDTO;
import com.neoris.app.serviciopersonas.entities.Cliente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
@Component
public interface ClienteMapper {
    ClienteDTO aClienteDTO(Cliente c);
    Cliente aCliente(ClienteDTO dto);
    default List<ClienteDTO> aListaClienteDTO(List<Cliente> lista) {
        if (lista == null) return new ArrayList<>();
        return lista.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }
}
