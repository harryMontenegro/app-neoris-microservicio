package com.neoris.app.serviciomovimiento.services.implementaciones;

import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaDTO;
import com.neoris.app.serviciomovimiento.dto.MovimientoCuentaPaginableDTO;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.repository.MovimientoCuentaRepository;
import com.neoris.app.serviciomovimiento.services.reporte.GenerarReporteService;
import com.neoris.app.serviciomovimiento.services.MovimientoCuentaService;

import com.neoris.app.serviciomovimiento.services.mapper.MovimientoCuentaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovimientoCuentaServiceImpl implements MovimientoCuentaService, GenerarReporteService {

    private final MovimientoCuentaRepository repository;
    private final MovimientoCuentaMapper mapper;
    private final WebClient.Builder clienteWeb;

    @Override
    public MovimientoCuentaDTO crear(MovimientoCuentaDTO movimientoCuentaDTO) {
        movimientoCuentaDTO.setEstado(movimientoCuentaDTO.getEstado() != null ? movimientoCuentaDTO.getEstado() : true);
        return mapper.aDTO(repository.save(mapper.aEntity(movimientoCuentaDTO)));
    }

    @Override
    public MovimientoCuentaDTO modificar(MovimientoCuentaDTO movimientoCuentaDTO) {
        return crear(movimientoCuentaDTO);
    }

    @Override
    public MovimientoCuentaDTO buscarPorId(Long id) throws RegistroNoEncontradoException {
        return mapper.aDTO(repository.findById(id).orElseThrow(() -> new RegistroNoEncontradoException("Registro no encontrado")));
    }

    @Override
    public List<MovimientoCuentaDTO> listar(MovimientoCuentaPaginableDTO movimPaginable) {
        return Optional.of(repository.findAll(PageRequest.of(movimPaginable.getPagina(), movimPaginable.getCantidad())))
                .get()
                .get()
                .map(mapper::aDTO).collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }


    @Override
    public List<MovimientoCuentaDTO> buscarPorIdCuenta(Set<Long> lista) {
        return mapper.aListaDTO(repository.findAllByIdCuentaIn(lista));
    }

    @Override
    public List<MovimientoCuentaRepository.EntidadReporte> generarReporteMovimiento(String identificacion, LocalDate fechaInicio, LocalDate fechaFin) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fi = formato.format(fechaInicio);
        String ff = formato.format(fechaFin);
        return repository.findAllEntidadReporte(identificacion, fi, ff);
    }
}
