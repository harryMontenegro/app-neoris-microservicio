package com.neoris.app.serviciopersonas.services.implementaciones;

import com.neoris.app.serviciopersonas.dto.PaginableDTO;
import com.neoris.app.serviciopersonas.dto.PersonaDTO;
import com.neoris.app.serviciopersonas.entities.Persona;
import com.neoris.app.serviciopersonas.exceptions.RegistroNoEncontradoException;
import com.neoris.app.serviciopersonas.repository.PersonaRepository;
import com.neoris.app.serviciopersonas.services.PersonaService;
import com.neoris.app.serviciopersonas.services.mapper.PersonaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public PersonaDTO crear(PersonaDTO personaDTO) throws RegistroNoEncontradoException {
        Optional<Persona> persona = Optional.ofNullable(personaMapper.aPersona(buscarPorIdentificacion(personaDTO.getIdentificacion())));
        if (persona.isPresent()) {
            persona.map(p -> {
                personaDTO.setIdPersona(p.getIdPersona());
                return personaDTO;
            });
        }
        personaDTO.setEstado(personaDTO.getEstado() != null ? personaDTO.getEstado() : true);
        return personaMapper.aPersonaDTO(personaRepository.save(personaMapper.aPersona(personaDTO)));
    }

    @Override
    public PersonaDTO modificar(PersonaDTO personaDTO) throws RegistroNoEncontradoException {
        return crear(personaDTO);
    }

    @Override
    public PersonaDTO buscarPorId(Long id) throws RegistroNoEncontradoException {
        return personaMapper.aPersonaDTO(personaRepository.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("Registro con el id: " + id + " no encontrado")));
    }

    @Override
    public List<PersonaDTO> listar(PaginableDTO paginableDTO) {
        return Optional.ofNullable(personaRepository.findAll(PageRequest.of(paginableDTO.getPagina(), paginableDTO.getCantidad())))
                .get()
                .get()
                .map(p -> {
                    return personaMapper.aPersonaDTO(p);
                }).collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public PersonaDTO buscarPorIdentificacion(String identificacion) {
        return personaMapper.aPersonaDTO(personaRepository.findByIdentificacion(identificacion).orElse(null));
    }
}
