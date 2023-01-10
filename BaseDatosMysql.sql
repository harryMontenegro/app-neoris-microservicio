-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema persona_bd
-- -----------------------------------------------------
-- Base de datos microservicio persona-service
DROP SCHEMA IF EXISTS `persona_bd`;

-- -----------------------------------------------------
-- Schema persona_bd
--
-- Base de datos microservicio persona-service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `persona_bd` DEFAULT CHARACTER SET utf8;
-- -----------------------------------------------------
-- Schema cuenta_bd
-- -----------------------------------------------------
-- Base de datos de  microservicio cuenta-service
DROP SCHEMA IF EXISTS `cuenta_bd`;

-- -----------------------------------------------------
-- Schema cuenta_bd
--
-- Base de datos de  microservicio cuenta-service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cuenta_bd`;
-- -----------------------------------------------------
-- Schema movimiento_bd
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `movimiento_bd`;

-- -----------------------------------------------------
-- Schema movimiento_bd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movimiento_bd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `persona_bd` ;

-- -----------------------------------------------------
-- Table `persona_bd`.`genero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `persona_bd`.`genero`;

CREATE TABLE IF NOT EXISTS `persona_bd`.`genero`
(
    `idGenero` INT         NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `codigo`   VARCHAR(4)  NOT NULL COMMENT 'Codigo del genero',
    `nombre`   VARCHAR(15) NOT NULL COMMENT 'Nombre del genero',
    `estado`   TINYINT     NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idGenero`),
    UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE
)
    ENGINE = InnoDB
COMMENT
= 'Tabla que guarda los tipos de genero de las personas';


-- -----------------------------------------------------
-- Table `persona_bd`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `persona_bd`.`persona`;

CREATE TABLE IF NOT EXISTS `persona_bd`.`persona`
(
    `idPersona`      INT          NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `identificacion` VARCHAR(45)  NOT NULL COMMENT 'Numero de identificación de la persona',
    `nombres`        VARCHAR(250) NOT NULL COMMENT 'Nombre y Apellido de la persona',
    `edad`           INT          NULL COMMENT 'Edad de la persona',
    `idGenero`       INT          NOT NULL COMMENT 'Relación con la tabla genero de la persona',
    `direccion`      VARCHAR(250) NULL COMMENT 'Dirección de la persona',
    `telefono`       VARCHAR(10)  NOT NULL COMMENT 'Numero telefonico de la persona',
    `estado`         TINYINT      NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idPersona`, `idGenero`),
    INDEX            `fk_persona_genero_idx` (`idGenero` ASC) VISIBLE,
    CONSTRAINT `fk_persona_genero`
        FOREIGN KEY (`idGenero`)
            REFERENCES `persona_bd`.`genero` (`idGenero`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
COMMENT
= 'Tabla que guarda los datos básicos de las personas';


-- -----------------------------------------------------
-- Table `persona_bd`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `persona_bd`.`cliente`;

CREATE TABLE IF NOT EXISTS `persona_bd`.`cliente`
(
    `idCliente`   INT          NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `idPersona`   INT          NOT NULL COMMENT 'Llave foranea relacion con persona',
    `contrasenia` VARCHAR(500) NOT NULL COMMENT 'Contraseña del cliente',
    `estado`      TINYINT      NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idCliente`, `idPersona`),
    INDEX         `fk_persona_cliente_idx` (`idPersona` ASC) VISIBLE,
    CONSTRAINT `fk_persona_cliente`
        FOREIGN KEY (`idPersona`)
            REFERENCES `persona_bd`.`persona` (`idPersona`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
COMMENT
= 'Tabla que guarda los datos de ssession de la persona';

USE `cuenta_bd` ;

-- -----------------------------------------------------
-- Table `cuenta_bd`.`listastipocuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cuenta_bd`.`listastipocuenta`;

CREATE TABLE IF NOT EXISTS `cuenta_bd`.`listastipocuenta`
(
    `idLista` INT         NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `codigo`  VARCHAR(4)  NOT NULL COMMENT 'Codigo del tipo de cuenta',
    `nombre`  VARCHAR(45) NOT NULL COMMENT 'Nombre del tipo de cuenta',
    `estado`  TINYINT     NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idLista`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cuenta_bd`.`cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cuenta_bd`.`cuenta`;

CREATE TABLE IF NOT EXISTS `cuenta_bd`.`cuenta`
(
    `idCuenta`     INT            NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `idPersona`    INT            NOT NULL COMMENT 'Llave foranea para la tabla persona',
    `numeroCuenta` VARCHAR(200)   NOT NULL COMMENT 'Numero de cuenta de la persona',
    `idTipoCuenta` INT            NOT NULL COMMENT 'Llave foranea de la tabla tipocuenta',
    `saldoInicial` DECIMAL(17, 3) NOT NULL COMMENT 'Saldo inicial de apertura de la cuenta',
    `estado`       TINYINT        NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    `saldoActual`  DECIMAL(17, 3) NOT NULL,
    PRIMARY KEY (`idCuenta`),
    UNIQUE INDEX `Uk_IdPersona_numeroCuenta` (`idPersona` ASC, `numeroCuenta` ASC) VISIBLE,
    INDEX          `fk_cuenta_tipocuenta_idx` (`idTipoCuenta` ASC) VISIBLE,
    CONSTRAINT `fk_cuenta_tipocuenta`
        FOREIGN KEY (`idTipoCuenta`)
            REFERENCES `cuenta_bd`.`listastipocuenta` (`idLista`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
COMMENT
= 'Tabla que guarda información de las cuentas del cliente';

USE `movimiento_bd` ;

-- -----------------------------------------------------
-- Table `movimiento_bd`.`listastipomovimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movimiento_bd`.`listastipomovimiento`;

CREATE TABLE IF NOT EXISTS `movimiento_bd`.`listastipomovimiento`
(
    `idLista` INT         NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `codigo`  VARCHAR(4)  NOT NULL COMMENT 'Codigo del tipo de movimiento',
    `nombre`  VARCHAR(45) NOT NULL COMMENT 'Nombre del tipo de movimiento',
    `estado`  TINYINT     NOT NULL DEFAULT '1' COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idLista`)
)
    ENGINE = InnoDB
DEFAULT CHARACTER
SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `movimiento_bd`.`movimientocuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movimiento_bd`.`movimientocuenta`;

CREATE TABLE IF NOT EXISTS `movimiento_bd`.`movimientocuenta`
(
    `idMovimientoCuenta` INT            NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `idCuenta`           INT            NOT NULL COMMENT 'Id de la cuenta relacionada al movimiento',
    `fechaMovimiento`    DATETIME       NOT NULL COMMENT 'Fecha en la que se realizo el movimiento',
    `idTipoMovimiento`   INT            NOT NULL COMMENT 'Tipo de movimiento realizado',
    `valor`              DECIMAL(17, 3) NOT NULL COMMENT 'Valor del movimiento',
    `saldo`              DECIMAL(17, 3) NOT NULL COMMENT 'Saldo disponible',
    `estado`             TINYINT        NOT NULL DEFAULT 1,
    `movimiento`         VARCHAR(45)    NOT NULL COMMENT 'Descripción del movimiento generado',
    PRIMARY KEY (`idMovimientoCuenta`),
    INDEX                `fk_movimientocuenta_tipomovimiento_idx` (`idTipoMovimiento` ASC) VISIBLE,
    CONSTRAINT `fk_movimientocuenta_tipomovimiento`
        FOREIGN KEY (`idTipoMovimiento`)
            REFERENCES `movimiento_bd`.`listastipomovimiento` (`idLista`)
)
    ENGINE = InnoDB
DEFAULT CHARACTER
SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci
COMMENT
= 'Tabla que guarda los movimientos de las cuentas';


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;



USE `persona_bd`;

INSERT INTO persona_bd.genero (codigo, nombre, estado)
values ('F', 'Femenino', 1);
INSERT INTO persona_bd.genero (codigo, nombre, estado)
values ('M', 'Masculino', 1);

INSERT INTO persona_bd.persona (identificacion, nombres, edad, idGenero, direccion, telefono, estado)
values ('5284322', 'Mireya Diaz', 56, 1, 'Calle las palmas', '344556611', 1);
INSERT INTO persona_bd.cliente (idPersona, contrasenia, estado)
values (1, '12345', 1);

USE `cuenta_bd`;
INSERT INTO cuenta_bd.listastipocuenta (codigo, nombre, estado)
values ('AHO', 'Ahorro', 1);
INSERT INTO cuenta_bd.listastipocuenta (codigo, nombre, estado)
values ('COR', 'Corriente', 1);

INSERT INTO cuenta_bd.cuenta (idPersona, numeroCuenta, idTipoCuenta, saldoInicial, estado, saldoActual)
values (1, '478758', 1, 2000, 1, 2000);
INSERT INTO cuenta_bd.cuenta (idPersona, numeroCuenta, idTipoCuenta, saldoInicial, estado, saldoActual)
values (1, '585545', 2, 2000, 1, 5000);

USE `movimiento_bd`;
INSERT INTO movimiento_bd.listastipomovimiento (codigo, nombre, estado)
values ('CRED', 'Credito', 1);
INSERT INTO movimiento_bd.listastipomovimiento (codigo, nombre, estado)
values ('DEB', 'Debito', 1);

USE `movimiento_bd`;
INSERT INTO movimiento_bd.movimientocuenta (idCuenta, fechaMovimiento, idTipoMovimiento, valor, saldo, estado,
                                            movimiento)
values (1, '2023-01-07 14:00:00', 1, 0, 2000, 1, 'Deposito de 2000');
INSERT INTO movimiento_bd.movimientocuenta (idCuenta, fechaMovimiento, idTipoMovimiento, valor, saldo, estado,
                                            movimiento)
values (2, '2023-01-07 14:00:00', 1, 0, 5000, 1, 'Deposito de 5000');




