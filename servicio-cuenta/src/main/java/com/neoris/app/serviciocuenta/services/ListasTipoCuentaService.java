package com.neoris.app.serviciocuenta.services;

import com.neoris.app.serviciocuenta.dto.ListasTipoCuentaDTO;

import java.util.List;

public interface ListasTipoCuentaService {
    ListasTipoCuentaDTO crear(ListasTipoCuentaDTO listasTipoCuentaDTO);
    ListasTipoCuentaDTO modificar(ListasTipoCuentaDTO listasTipoCuentaDTO);
    ListasTipoCuentaDTO buscarPorId(Long id);
    List<ListasTipoCuentaDTO> listar();
    void eliminar(Long id);
}
