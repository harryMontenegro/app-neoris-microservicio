package com.neoris.app.serviciomovimiento.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaDTO;
import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaPaginableDTO;
import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.exceptions.TransaccionNoValidaException;
import com.neoris.app.serviciomovimiento.services.GenerarMovimiento;
import com.neoris.app.serviciomovimiento.services.reporte.GenerarReporteService;
import com.neoris.app.serviciomovimiento.services.MovimientoCuentaService;
import com.neoris.app.serviciomovimiento.services.implementaciones.MovimientoCuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;


@RestController
@RequestMapping("/movimiento")
public class MovimientoCuentaController {
    private final MovimientoCuentaService service;
    private final GenerarMovimiento movimiento;

    private final GenerarReporteService reporte;

    @Autowired
    public MovimientoCuentaController(MovimientoCuentaServiceImpl serviceImpl, GenerarMovimiento movimiento, GenerarReporteService reporte) {
        this.service = serviceImpl;
        this.movimiento = movimiento;
        this.reporte = reporte;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorId(id));
    }

    @PostMapping("/generarMovimiento")
    public void movimientoAhorro(@Valid @RequestBody PeticionTransaccion request) throws RegistroNoEncontradoException, TransaccionNoValidaException {
        movimiento.generarMovimiento(request).aplicarOperacionCuenta();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody MovimientoCuentaDTO movimientoCta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(movimientoCta));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody MovimientoCuentaDTO movimientoCta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(movimientoCta));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/reporte")
    @ResponseBody
    public ResponseEntity<?> reporteMovimiento(@RequestParam String numeroDoc,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) throws RegistroNoEncontradoException, JsonProcessingException {


        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reporte.generarReporteMovimiento(numeroDoc, fechaInicio, fechaFin));
    }
}