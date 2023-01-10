package com.neoris.app.serviciomovimiento.services.implementaciones.fabricatransaccion;

import com.neoris.app.serviciomovimiento.dto.CuentaRespuesta;
import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaDTO;
import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.exceptions.TransaccionNoValidaException;
import com.neoris.app.serviciomovimiento.services.ListasTipoMovimientoService;
import com.neoris.app.serviciomovimiento.services.MovimientoCuentaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import static com.neoris.app.serviciomovimiento.services.enums.ServiciosApi.Cuenta;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Transaccion {

    protected PeticionTransaccion datosTransaccion;
    protected WebClient.Builder clienteWeb;
    protected MovimientoCuentaService service;
    protected ListasTipoMovimientoService serviceTipoMov;


    public void aplicarOperacionCuenta() throws TransaccionNoValidaException, RegistroNoEncontradoException {

        if (validarMontoDisponible()) {
            aplicarTransaccionCuenta();
            generarMovientoCuenta();
        } else {
            throw new TransaccionNoValidaException("No es posible realizar la transacción, por favor revise su información");
        }
    }

    protected void generarMovientoCuenta() throws RegistroNoEncontradoException {
        CuentaRespuesta cuentaRespuesta = obtenerCuenta()
                .orElseThrow(() -> new RegistroNoEncontradoException("Numero de cuenta no existe"));

        ListasTipoMovimientoDTO tipoMov = serviceTipoMov.buscarPorCodigo(datosTransaccion.getCodigoTipoTransaccion());
        Function<CuentaRespuesta, MovimientoCuentaDTO> generarMovimiento = c ->
                new MovimientoCuentaDTO(c, tipoMov.getIdLista(), datosTransaccion.getValorTransacion(),
                        generarDescripcion(datosTransaccion.getCodigoTipoTransaccion(), datosTransaccion.getValorTransacion()));

        MovimientoCuentaDTO movimientoAGenerar = generarMovimiento.apply(cuentaRespuesta);
        service.crear(movimientoAGenerar);
    }

    protected String generarDescripcion(String codigo, BigDecimal valor) {
        return codigo != null && codigo.equals("CRED") ? "Deposito de " + valor : "Retiro de " + valor;
    }

    protected Optional<CuentaRespuesta> obtenerCuenta()  {
        return Optional.ofNullable(clienteWeb.build()
                .get()
                .uri(Cuenta.getUrl() + "cuenta/numeroCuenta/" + datosTransaccion.getNumeroCuenta())
                .retrieve()
                .bodyToMono(CuentaRespuesta.class)
                .onErrorResume(WebClientResponseException.class,
                        e -> e.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(e))
                .block());
    }

    protected abstract Boolean validarMontoDisponible() throws RegistroNoEncontradoException, TransaccionNoValidaException;

    protected abstract void aplicarTransaccionCuenta() throws RegistroNoEncontradoException, TransaccionNoValidaException;
}
