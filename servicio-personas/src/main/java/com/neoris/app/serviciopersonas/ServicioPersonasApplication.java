package com.neoris.app.serviciopersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicioPersonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioPersonasApplication.class, args);
	}

}
