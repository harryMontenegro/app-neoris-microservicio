package com.neoris.app.serviciomovimiento.services.reporte;

import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.repository.MovimientoCuentaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GenerarReporteService {
    List<MovimientoCuentaRepository.EntidadReporte> generarReporteMovimiento(String numeroDocumento, LocalDate fechaInicio, LocalDate fechaFin) throws RegistroNoEncontradoException;
}
