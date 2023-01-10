package com.neoris.app.serviciocuenta.services.implementaciones;

import com.neoris.app.serviciocuenta.dto.CuentaDTO;
import com.neoris.app.serviciocuenta.dto.PersonaRespuesta;
import com.neoris.app.serviciocuenta.entities.Cuenta;
import com.neoris.app.serviciocuenta.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciocuenta.repository.CuentaRepository;
import com.neoris.app.serviciocuenta.services.CuentaService;
import static com.neoris.app.serviciocuenta.services.enums.ServiciosApi.*;
import com.neoris.app.serviciocuenta.services.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Formatter;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;
    private final CuentaMapper mapper;
    private final WebClient.Builder clienteWeb;

    @Autowired
    public CuentaServiceImpl(CuentaRepository repository, CuentaMapper mapper, WebClient.Builder clienteWeb) {
        this.repository = repository;
        this.mapper = mapper;
        this.clienteWeb = clienteWeb;
    }

    @Override
    public CuentaDTO crear(CuentaDTO cuentaDTO) throws RegistroNoEncontradoException {
        Optional<Cuenta> cuentaGuardada = repository.findCuentaByIdPersonaAndNumeroCuenta(cuentaDTO.getIdPersona(), cuentaDTO.getNumeroCuenta());
        if (cuentaGuardada.isEmpty()) {
            cuentaDTO.setNumeroCuenta(String.valueOf(new Formatter().format("%06d", ((int) (Math.random() * 999999) + 1))));
        } else {
            cuentaGuardada.ifPresent(cu -> cuentaDTO.setIdCuenta(cu.getIdCuenta()));
        }
        cuentaDTO.setEstado(cuentaDTO.getEstado() != null ? cuentaDTO.getEstado() : true);
        return buscarPorId(repository.save(mapper.aEntity(cuentaDTO)).getIdCuenta());
    }

    @Override
    public CuentaDTO modificar(CuentaDTO cuentaDTO) throws RegistroNoEncontradoException {
        return crear(cuentaDTO);
    }

    @Override
    public CuentaDTO buscarPorId(Long id) throws RegistroNoEncontradoException {
        return mapper.aDTO(repository.findById(id).orElseThrow(() ->
                new RegistroNoEncontradoException("Registro con id: " + id + " no encontrado")));
    }


    public List<CuentaDTO> buscarPorIdPersona(Long id) throws RegistroNoEncontradoException {
        return mapper.aListaDTO(repository.findAllByIdPersona(id).orElseThrow(() ->
                new RegistroNoEncontradoException("Registro con id: " + id + " no encontrado")));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    //.retrieve() sirve para poder recibir la repuesta
    //.block() sirve para convertor la petición en síncrona por defecto es asíncrona
    @Override
    public List<CuentaDTO> buscarPorIdentificacion(String identificacion) throws RegistroNoEncontradoException {
        return Optional.ofNullable(clienteWeb.build().get()
                .uri(Persona.getUrl() + "persona/numeroDocumento/" + identificacion)
                .retrieve()
                .bodyToMono(PersonaRespuesta.class)
                .block())
                .map(PersonaRespuesta::getIdPersona)
                .map(id -> {
                    try {
                        return buscarPorIdPersona(id);
                    } catch (RegistroNoEncontradoException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseThrow(() -> new RegistroNoEncontradoException("No existen registros con el numero de documento"));
    }

    @Override
    public CuentaDTO buscarPorNumeroDeCuenta(String numeroCuenta) throws RegistroNoEncontradoException {
        return repository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RegistroNoEncontradoException("El número de cuenta no encotrado"));
    }
}
