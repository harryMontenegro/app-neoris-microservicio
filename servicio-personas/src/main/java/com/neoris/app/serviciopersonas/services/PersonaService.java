package com.neoris.app.serviciopersonas.services;

import com.neoris.app.serviciopersonas.dto.PaginableDTO;
import com.neoris.app.serviciopersonas.dto.PersonaDTO;
import com.neoris.app.serviciopersonas.exceptions.RegistroNoEncontradoException;

import java.util.List;

public interface PersonaService {
    PersonaDTO crear(PersonaDTO personaDTO) throws RegistroNoEncontradoException;
    PersonaDTO modificar(PersonaDTO personaDTO) throws RegistroNoEncontradoException;
    PersonaDTO buscarPorId(Long id) throws RegistroNoEncontradoException;
    List<PersonaDTO> listar(PaginableDTO paginableDTO);
    void eliminar(Long id);
    PersonaDTO buscarPorIdentificacion(String identificacion) throws RegistroNoEncontradoException;
}
