package com.neoris.app.serviciopersonas.services.mapper;

import com.neoris.app.serviciopersonas.dto.PersonaDTO;
import com.neoris.app.serviciopersonas.entities.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
@Component
public interface PersonaMapper {
    @Mappings({
            @Mapping(source = "idPersona", target = "idPersona"),
            @Mapping(source = "identificacion", target = "identificacion"),
            @Mapping(source = "nombres", target = "nombres"),
            @Mapping(source = "idGenero", target = "idGenero"),
            @Mapping(source = "edad", target = "edad"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "estado", target = "estado")
            }
    )
    PersonaDTO aPersonaDTO(Persona p);
    Persona aPersona(PersonaDTO dto);
    default List<PersonaDTO> aListaPersonaDTO(List<Persona> lista) {
        if (lista == null) return new ArrayList<>();
        return lista.stream().map(PersonaDTO::new).collect(Collectors.toList());
    }
}
