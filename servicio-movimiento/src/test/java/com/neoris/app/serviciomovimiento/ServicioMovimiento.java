package com.neoris.app.serviciomovimiento;

import com.neoris.app.serviciomovimiento.dto.PeticionTransaccion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
class ServicioMovimiento {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	public void generarMovimientoDebitoConSaldoDisponiblePositivo() throws Exception {

		mvc.perform(
						MockMvcRequestBuilders.post("/movimiento/generarMovimiento")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(PeticionTransaccion.builder()
										.numeroCuenta("555555")
										.valorTransacion(new BigDecimal("50000"))
										.codigoTipoTransaccion("CRED")
										.build())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.fechaMaximaDevolucion").exists())
				.andReturn();


	}
}
