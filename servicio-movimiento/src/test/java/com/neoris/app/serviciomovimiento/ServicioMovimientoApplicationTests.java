package com.neoris.app.serviciomovimiento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import com.neoris.app.serviciomovimiento.repository.MovimientoCuentaRepository;
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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ServicioMovimientoApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovimientoCuentaRepository repository;

    @Container
    private static final MySQLContainer contenedorMysql = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("movimiento_bd")
            .withUsername("root")
            .withPassword("prueba");

    @DynamicPropertySource
    private static void setPropiedades(DynamicPropertyRegistry propiedades) {
        propiedades.add("spring.datasource.url", contenedorMysql::getJdbcUrl);
        propiedades.add("spring.datasource.username", contenedorMysql::getUsername);
        propiedades.add("spring.datasource.password", contenedorMysql::getPassword);
    }

    private PeticionTransaccion crearPeticionDeMovimiento() {
        return PeticionTransaccion.builder()
                .numeroCuenta("555555")
                .valorTransacion(new BigDecimal("50000"))
                .codigoTipoTransaccion("CRED")
                .build();
    }

    @Test
    void generarMovientoCuentaDebitoConSaldoPositivo() throws Exception {
        PeticionTransaccion transaccion = crearPeticionDeMovimiento();
        mockMvc.perform(MockMvcRequestBuilders.post("/movimiento/generarMovimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaccion))
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }
}
















