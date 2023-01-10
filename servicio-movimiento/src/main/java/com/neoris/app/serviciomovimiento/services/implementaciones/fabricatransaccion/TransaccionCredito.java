package com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion;

import com.neoris.app.serviciomovimiento.dto.CuentaRespuesta;
import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import com.neoris.app.serviciomovimiento.services.ListasTipoMovimientoService;
import com.neoris.app.serviciomovimiento.services.MovimientoCuentaService;


import static com.neoris.app.serviciomovimiento.services.enums.ServiciosApi.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Getter
@Setter
@NoArgsConstructor
@Component
public class TransaccionCredito extends Transaccion {


    public TransaccionCredito(PeticionTransaccion datosTransaccion, WebClient.Builder clienteWeb, MovimientoCuentaService service, ListasTipoMovimientoService serviceTipoMov) {
        super(datosTransaccion, clienteWeb, service, serviceTipoMov);
    }

    @Override
    public Boolean validarMontoDisponible() {
        return obtenerCuenta().isPresent();
    }

    @Override
    public void aplicarTransaccionCuenta() {
        obtenerCuenta()
                .ifPresent(c -> {
                    c.setSaldoActual(c.getSaldoActual().add(getDatosTransaccion().getValorTransacion()));
                    clienteWeb.build()
                            .post()
                            .uri(Cuenta.getUrl() + "cuenta")
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(c))
                            .retrieve()
                            .bodyToMono(CuentaRespuesta.class)
                            .block();
                });
    }
}
