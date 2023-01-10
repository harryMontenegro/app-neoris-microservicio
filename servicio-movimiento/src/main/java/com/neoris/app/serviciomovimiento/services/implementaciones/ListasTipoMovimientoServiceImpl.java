package com.neoris.app.serviciomovimiento.services.implementaciones;

import com.neoris.app.serviciomovimiento.dto.ListasTipoMovimientoDTO;
import com.neoris.app.serviciomovimiento.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciomovimiento.repository.ListasTipoMovimientoRepository;
import com.neoris.app.serviciomovimiento.services.ListasTipoMovimientoService;
import com.neoris.app.serviciomovimiento.services.mapper.ListasTipoMovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListasTipoMovimientoServiceImpl implements ListasTipoMovimientoService {

    private final ListasTipoMovimientoRepository repositorio;
    private final ListasTipoMovimientoMapper mapper;

    @Autowired
    public ListasTipoMovimientoServiceImpl(ListasTipoMovimientoRepository repositorio, ListasTipoMovimientoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public ListasTipoMovimientoDTO crear(ListasTipoMovimientoDTO lista) {
        ListasTipoMovimientoDTO listaTipo = buscarPorCodigo(lista.getCodigo());
        if (listaTipo != null){
            lista.setIdLista(listaTipo.getIdLista());
        }
        return mapper.aDTO(repositorio.save(mapper.aEntity(lista)));
    }

    @Override
    public ListasTipoMovimientoDTO modificar(ListasTipoMovimientoDTO lista) {
        return crear(lista);
    }

    @Override
    public ListasTipoMovimientoDTO buscarPorId(Integer id) {
        return mapper.aDTO(repositorio.findById(id).orElse(null));
    }

    @Override
    public List<ListasTipoMovimientoDTO> listar() {
        return mapper.aListaDTO(repositorio.findAll());
    }

    @Override
    public void eliminar(Integer id) {
        repositorio.deleteById(id);
    }

    @Override
    public ListasTipoMovimientoDTO buscarPorCodigo(String codigo) {
        return repositorio.findByCodigo(codigo);
    }
}
