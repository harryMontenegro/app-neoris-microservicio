package com.neoris.app.serviciocuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicioCuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioCuentaApplication.class, args);
	}

}
