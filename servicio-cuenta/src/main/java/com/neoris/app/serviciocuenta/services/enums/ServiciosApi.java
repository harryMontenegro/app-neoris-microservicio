package com.neoris.app.serviciocuenta.services.enums;


public enum ServiciosApi {

    Persona("http://servicio-persona/"),
    Cuenta("http://servicio-cuenta/"),
    Movimiento("http://servicio-movimiento/");

    String url;

    ServiciosApi(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
