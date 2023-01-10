package com.neoris.app.serviciomovimiento.controller;

import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.services.ListasTipoMovimientoService;
import com.neoris.app.serviciomovimiento.services.implementaciones.ListasTipoMovimientoServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@RestController
@RequestMapping("/listastipomovimiento")
public class ListasTipoMovimientoController {
    
    private ListasTipoMovimientoService service;

    public ListasTipoMovimientoController(ListasTipoMovimientoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.listar());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws RegistroNoEncontradoException {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ListasTipoMovimientoDTO listaTipoMov) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(listaTipoMov));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody ListasTipoMovimientoDTO listaTipoMov) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(listaTipoMov));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
