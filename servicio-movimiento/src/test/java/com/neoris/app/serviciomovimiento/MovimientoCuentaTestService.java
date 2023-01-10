package com.neoris.app.serviciomovimiento;

import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.exceptions.TransaccionNoValidaException;
import com.neoris.app.serviciomovimiento.services.GenerarMovimiento;
import com.neoris.app.serviciomovimiento.services.MovimientoCuentaService;
import com.neoris.app.serviciomovimiento.services.implementaciones.MovimientoCuentaServiceImpl;
import com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion.TransaccionCredito;
import com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion.TransaccionDebito;
import com.neoris.app.serviciomovimiento.services.reporte.GenerarReporteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class MovimientoCuentaTestService {

    private final MovimientoCuentaService service;
    private final GenerarMovimiento movimiento;
    private final GenerarReporteService reporte;
    private final TransaccionCredito credito;
    private final TransaccionDebito debito;

    @Autowired
    public MovimientoCuentaTestService(MovimientoCuentaServiceImpl serviceImpl, GenerarMovimiento movimiento, GenerarReporteService reporte, TransaccionCredito credito, TransaccionDebito debito) {
        this.service = serviceImpl;
        this.movimiento = movimiento;
        this.reporte = reporte;
        this.credito = credito;
        this.debito = debito;
    }

    @BeforeAll
    static PeticionTransaccion crearDatosDePrueba(){
        return PeticionTransaccion.builder()
                .numeroCuenta("555555")
                .valorTransacion(new BigDecimal("50000"))
                .codigoTipoTransaccion("CRED")
                .build();
    }
    @Test
    void generarMovimiento() throws RegistroNoEncontradoException, TransaccionNoValidaException {
        movimiento.generarMovimiento(crearDatosDePrueba()).aplicarOperacionCuenta();

    }
}
