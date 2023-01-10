package com.neoris.app.serviciopersonas.controller;

import com.neoris.app.serviciopersonas.dto.ClienteDTO;
import com.neoris.app.serviciopersonas.dto.PaginableDTO;
import com.neoris.app.serviciopersonas.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciopersonas.services.ClienteService;
import com.neoris.app.serviciopersonas.services.implementaciones.ClienteServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteServiceimpl clienteServiceimpl) {
        this.service = clienteServiceimpl;
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.listar(paginableDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorId(id));
    }

    @GetMapping("/numeroDocumento/{identificacion}")
    public ResponseEntity<?> buscarPorIdentificacion(@PathVariable String identificacion) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorIdentificacion(identificacion));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteDTO clienteDTO) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(clienteDTO));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody ClienteDTO clienteDTO) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(clienteDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
