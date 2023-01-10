package com.neoris.app.serviciocuenta.services;

import com.neoris.app.serviciocuenta.dto.CuentaDTO;
import com.neoris.app.serviciocuenta.exceptions.RegistroNoEncontradoException;

import java.util.List;

public interface CuentaService {
    CuentaDTO crear(CuentaDTO cuentaDTO) throws RegistroNoEncontradoException;
    CuentaDTO modificar(CuentaDTO cuentaDTO) throws RegistroNoEncontradoException;
    CuentaDTO buscarPorId(Long id) throws RegistroNoEncontradoException;
    void eliminar(Long id);
    List<CuentaDTO> buscarPorIdentificacion(String identificacion) throws RegistroNoEncontradoException;
    CuentaDTO buscarPorNumeroDeCuenta(String numeroCuenta) throws RegistroNoEncontradoException;

}
