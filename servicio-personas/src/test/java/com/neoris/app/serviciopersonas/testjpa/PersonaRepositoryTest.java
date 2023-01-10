package com.neoris.app.serviciopersonas.testjpa;
import com.neoris.app.serviciopersonas.repository.GeneroRepository;
import com.neoris.app.serviciopersonas.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;


@SpringBootTest
public class PersonaRepositoryTest {

    private final PersonaRepository personaRepository;
    private final GeneroRepository generoRepository;

    @Autowired
    public PersonaRepositoryTest(PersonaRepository personaRepository, GeneroRepository generoRepository) {
        this.personaRepository = personaRepository;
        this.generoRepository = generoRepository;
    }

    @Transactional
    @Test
    void validarGuardado(){
/*        Genero genero = Genero.builder()
                .codigo("M")
                .nombre("Masculino")
                .estado(true)
                .build();
        genero = generoRepository.save(genero);

        Persona persona = Persona.builder()
                .identificacion("109812345")
                .nombres("Jose Lema")
                .idGenero(genero.getIdGenero())
                .edad(25)
                .direccion("Otavalo sn y principal")
                .telefono("098254785")
                .estado(true).build();
        personaRepository.save(persona);

        persona = personaRepository.getById(1L);
        Assertions.assertEquals(1, persona.getIdPersona());*/
    }

}
