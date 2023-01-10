package com.neoris.app.serviciocuenta.controller;

import com.neoris.app.serviciocuenta.dto.ListasTipoCuentaDTO;
import com.neoris.app.serviciocuenta.services.ListasTipoCuentaService;
import com.neoris.app.serviciocuenta.services.implementaciones.ListasTipoCuentaServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@RestController
@RequestMapping("/tipoCuenta")
public class ListasTipoCuentaController {
    
    private ListasTipoCuentaService service;

    public ListasTipoCuentaController(ListasTipoCuentaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.listar());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ListasTipoCuentaDTO listasTipoCuentaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(listasTipoCuentaDTO));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody ListasTipoCuentaDTO listasTipoCuentaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(listasTipoCuentaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
