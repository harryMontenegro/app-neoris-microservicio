package com.neoris.app.serviciomovimiento.services;


import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaDTO;
import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaPaginableDTO;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface MovimientoCuentaService {
    MovimientoCuentaDTO crear(MovimientoCuentaDTO movimientoCuentaDTO);
    MovimientoCuentaDTO modificar(MovimientoCuentaDTO movimientoCuentaDTO);
    MovimientoCuentaDTO buscarPorId(Long id) throws RegistroNoEncontradoException;
    List<MovimientoCuentaDTO> listar(MovimientoCuentaPaginableDTO movimientoCuentaPaginableDTO);
    void eliminar(Long id);
    List<MovimientoCuentaDTO> buscarPorIdCuenta(Set<Long> lista);
}
