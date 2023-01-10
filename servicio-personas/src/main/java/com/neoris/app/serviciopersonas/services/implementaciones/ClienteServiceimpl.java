package com.neoris.app.serviciopersonas.services.implementaciones;

import com.neoris.app.serviciopersonas.dto.ClienteDTO;
import com.neoris.app.serviciopersonas.dto.PaginableDTO;
import com.neoris.app.serviciopersonas.entities.Cliente;
import com.neoris.app.serviciopersonas.entities.Persona;
import com.neoris.app.serviciopersonas.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciopersonas.repository.ClienteRepository;
import com.neoris.app.serviciopersonas.services.ClienteService;
import com.neoris.app.serviciopersonas.services.mapper.ClienteMapper;
import com.neoris.app.serviciopersonas.services.mapper.PersonaMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceimpl implements ClienteService {

    private ClienteRepository repository;
    private ClienteMapper mapper;

    private PersonaMapper personaMapper;

    @Override
    public ClienteDTO crear(ClienteDTO clienteDto) {
        Optional<Persona> persona = Optional.ofNullable(personaMapper.aPersona(buscarPorIdentificacion(clienteDto.getIdentificacion())));
        if(persona.isPresent()){
            persona.map(p -> {
                clienteDto.setIdPersona(p.getIdPersona());
                return clienteDto;
            });
        }
        clienteDto.setEstado(clienteDto.getEstado() != null ? clienteDto.getEstado() : true);
        ClienteDTO guardado = buscarPorIdentificacion(clienteDto.getIdentificacion());
        clienteDto.setIdCliente(guardado != null && guardado.getIdCliente() != null ? guardado.getIdCliente() : null);
        mapper.aClienteDTO(repository.save(mapper.aCliente(clienteDto)));
        return buscarPorIdentificacion(clienteDto.getIdentificacion());
    }

    @Override
    public ClienteDTO modificar(ClienteDTO personaDTO) throws RegistroNoEncontradoException {
        return crear(personaDTO);
    }

    @Override
    public ClienteDTO buscarPorId(Long id) throws RegistroNoEncontradoException {
        return mapper.aClienteDTO(repository.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("Registro con el id: " + id + " no encontrado")));
    }

    @Override
    public List<ClienteDTO> listar(PaginableDTO paginableDTO) {
        return Optional.of(repository.findAll(PageRequest.of(paginableDTO.getPagina(), paginableDTO.getCantidad())))
                .get()
                .get()
                .map(c -> mapper.aClienteDTO(c)).collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ClienteDTO buscarPorIdentificacion(String identificacion) {
        return mapper.aClienteDTO(repository.findByPersona_Identificacion(identificacion)
                .orElse(null));
    }

    public ClienteDTO buscarPorIdPersona(Long idPersona){
        return mapper.aClienteDTO(repository.findByIdPersona(idPersona).orElse(null));
    }
}
