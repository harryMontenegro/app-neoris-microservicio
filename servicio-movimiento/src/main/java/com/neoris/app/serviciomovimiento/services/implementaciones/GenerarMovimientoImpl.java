package com.neoris.app.serviciomovimiento.services.implementaciones;

import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;;
import com.neoris.app.serviciomovimiento.exceptions.TransaccionNoValidaException;
import com.neoris.app.serviciomovimiento.services.GenerarMovimiento;

import com.neoris.app.serviciomovimiento.services.ListasTipoMovimientoService;
import com.neoris.app.serviciomovimiento.services.MovimientoCuentaService;
import com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion.Transaccion;
import com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion.TransaccionCredito;
import com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion.TransaccionDebito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GenerarMovimientoImpl implements GenerarMovimiento {

    private MovimientoCuentaService service;
    private ListasTipoMovimientoService serviceTipoMov;
    private WebClient.Builder clienteWeb;

    @Autowired
    public GenerarMovimientoImpl(MovimientoCuentaService service, WebClient.Builder clienteWeb, ListasTipoMovimientoService serviceTipoMov) {
        this.service = service;
        this.clienteWeb = clienteWeb;
        this.serviceTipoMov = serviceTipoMov;
    }

    @Override
    public Transaccion generarMovimiento(PeticionTransaccion peticion) throws TransaccionNoValidaException {
        Transaccion trx;
        switch (peticion.getCodigoTipoTransaccion()){
            case "CRED":{
                trx = new TransaccionCredito(peticion, clienteWeb, service, serviceTipoMov);
                break;
            }
            case "DEB":{
                trx = new TransaccionDebito(peticion, clienteWeb, service, serviceTipoMov);
                break;
            }
            default:{
                throw new TransaccionNoValidaException("Código de transacción desconocido");
            }
        }
        return trx;
    }
}
