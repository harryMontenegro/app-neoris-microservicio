package com.neoris.app.serviciocuenta.controller;

import com.neoris.app.serviciocuenta.dto.CuentaDTO;
import com.neoris.app.serviciocuenta.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciocuenta.services.CuentaService;
import com.neoris.app.serviciocuenta.services.implementaciones.CuentaServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService service;

    @Autowired
    public CuentaController(CuentaServiceImpl cuentaService) {
        this.service = cuentaService;
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorId(id));
    }

    @GetMapping("/numeroDocumento/{identificacion}")
    public ResponseEntity<?> buscarPorIdentificacion(@PathVariable String identificacion) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorIdentificacion(identificacion));
    }

    @GetMapping("/numeroCuenta/{numeroCuenta}")
    public ResponseEntity<?> buscarPorNumeroCuenta(@PathVariable String numeroCuenta) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorNumeroDeCuenta(numeroCuenta));
    }
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CuentaDTO cuentaDTO) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(cuentaDTO));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody CuentaDTO cuentaDTO) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(cuentaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
