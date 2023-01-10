package com.neoris.app.serviciopersonas.controller;

import com.neoris.app.serviciopersonas.dto.PaginableDTO;
import com.neoris.app.serviciopersonas.dto.PersonaDTO;
import com.neoris.app.serviciopersonas.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciopersonas.services.PersonaService;
import com.neoris.app.serviciopersonas.services.implementaciones.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/persona")
public class PersonaController {
    private final PersonaService service;

    @Autowired
    public PersonaController(PersonaServiceImpl personaService) {
        this.service = personaService;
    }

    @PostMapping("/buscar")
    public ResponseEntity<?> buscarTodos(@RequestBody  PaginableDTO paginableDTO){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.listar(paginableDTO));
    }

    @GetMapping("/{idPersona}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idPersona) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorId(idPersona));
    }

    @GetMapping("/numeroDocumento/{identificacion}")
    public ResponseEntity<?> buscarPorIdentificacion(@PathVariable String identificacion) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorIdentificacion(identificacion));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PersonaDTO personaDTO) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(personaDTO));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody PersonaDTO personaDTO) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(personaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
