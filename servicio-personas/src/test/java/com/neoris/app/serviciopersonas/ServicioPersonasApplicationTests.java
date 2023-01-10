package com.neoris.app.serviciopersonas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.app.serviciopersonas.dto.PersonaDTO;
import com.neoris.app.serviciopersonas.entities.Persona;
import com.neoris.app.serviciopersonas.repository.PersonaRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ServicioPersonasApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonaRepository personaRepository;

    @Container
    private static final MySQLContainer contenedorMysql = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("persona_bd")
            .withUsername("root")
            .withPassword("prueba");

    @DynamicPropertySource
    private static void setPropiedades(DynamicPropertyRegistry propiedades) {
        propiedades.add("spring.datasource.url", contenedorMysql::getJdbcUrl);
        propiedades.add("spring.datasource.username", contenedorMysql::getUsername);
        propiedades.add("spring.datasource.password", contenedorMysql::getPassword);
    }

    @Test
    void deberiaCrearPersona() throws Exception {
        PersonaDTO personaDTO = crearPersona();
        mockMvc.perform(MockMvcRequestBuilders.post("/persona")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personaDTO))
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPersona").exists());

        Persona p = personaRepository.getReferenceById(1L);
        assertNotNull(p);
        assertEquals("1098112233", p.getIdentificacion());
    }
    private PersonaDTO crearPersona() {
        return PersonaDTO.builder()
                .identificacion("1098112233")
                .nombres("Jose Lema")
                .idGenero(1)
                .edad(28)
                .direccion("Otavalo sn y principal")
                .telefono("098254785")
                .estado(true)
                .build();
    }
}
















