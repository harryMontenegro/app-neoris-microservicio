package com.neoris.app.serviciopersonas.services;

import com.neoris.app.serviciopersonas.dto.ClienteDTO;
import com.neoris.app.serviciopersonas.dto.PaginableDTO;
import com.neoris.app.serviciopersonas.exceptions.RegistroNoEncontradoException;

import java.util.List;

public interface ClienteService {
    ClienteDTO crear(ClienteDTO personaDTO) throws RegistroNoEncontradoException;
    ClienteDTO modificar(ClienteDTO personaDTO) throws RegistroNoEncontradoException;
    ClienteDTO buscarPorId(Long id) throws RegistroNoEncontradoException;
    List<ClienteDTO> listar(PaginableDTO paginableDTO);
    void eliminar(Long id);
    ClienteDTO buscarPorIdentificacion(String identificacion) throws RegistroNoEncontradoException;

}
