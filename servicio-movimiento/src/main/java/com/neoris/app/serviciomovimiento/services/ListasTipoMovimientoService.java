package com.neoris.app.serviciomovimiento.services;
import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;

import java.util.List;

public interface ListasTipoMovimientoService {
    ListasTipoMovimientoDTO crear(ListasTipoMovimientoDTO lista);
    ListasTipoMovimientoDTO modificar(ListasTipoMovimientoDTO lista);
    ListasTipoMovimientoDTO buscarPorId(Integer id) throws RegistroNoEncontradoException;
    List<ListasTipoMovimientoDTO> listar();
    void eliminar(Integer id);
    ListasTipoMovimientoDTO buscarPorCodigo(String codigo);
}
