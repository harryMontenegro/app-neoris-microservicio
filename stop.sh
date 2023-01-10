#!/usr/bin/env bash

docker stop servicio-discovery && docker rm servicio-discovery
docker stop servicio-apigateway && docker rm servicio-apigateway
docker stop servicio-personas && docker rm servicio-personas
docker stop servicio-cuenta && docker rm servicio-cuenta
docker stop servicio-movimiento && docker rm servicio-movimiento
docker stop mysql-persona && docker rm mysql-persona
docker stop mysql-cuenta && docker rm mysql-cuenta
docker stop mysql-movimiento && docker rm mysql-movimiento