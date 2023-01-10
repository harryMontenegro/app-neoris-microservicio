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
}
