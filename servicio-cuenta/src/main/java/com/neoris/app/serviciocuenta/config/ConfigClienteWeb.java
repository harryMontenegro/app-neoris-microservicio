package com.neoris.app.serviciocuenta.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigClienteWeb {
    //@LoadBalanced permite especificar un balanceador de carga en caso de que se tenga más de 1 instancia de los microservicios
    //WebClient.Builder y WebClient.builder() prepara el bean para que se aplique el balanceador de carga al realizar las peticiones. NOTA:
    //Ver como se aplica en la clase CuentaServiceImpl en la liena 71 ("clienteWeb.build().get()")
    @Bean
    @LoadBalanced
    WebClient.Builder clienteWeb(){
        return  WebClient.builder();
    }
}
