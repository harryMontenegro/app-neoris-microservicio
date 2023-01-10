package com.neoris.app.serviciomovimiento.services;

import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.exceptions.TransaccionNoValidaException;
import com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion.Transaccion;

public interface GenerarMovimiento {
    Transaccion generarMovimiento(PeticionTransaccion peticion) throws RegistroNoEncontradoException, TransaccionNoValidaException;
}
