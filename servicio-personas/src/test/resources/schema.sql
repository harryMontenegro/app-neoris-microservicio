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
-- Base de datos para microservicio movimiento_service
DROP SCHEMA IF EXISTS `movimiento_bd`;

-- -----------------------------------------------------
-- Schema movimiento_bd
--
-- Base de datos para microservicio movimiento_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movimiento_bd`;
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
    PRIMARY KEY (`idPersona`),
    INDEX            `fk_persona_genero_idx` (`idGenero` ASC) VISIBLE,
    UNIQUE INDEX `Uk_identificacion` (`identificacion` ASC) VISIBLE,
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
    PRIMARY KEY (`idCliente`),
    INDEX         `fk_persona_cliente_idx` (`idPersona` ASC) VISIBLE,
    UNIQUE INDEX `idPersona_UNIQUE` (`idPersona` ASC) VISIBLE,
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
-- Table `cuenta_bd`.`tipocuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cuenta_bd`.`tipocuenta`;

CREATE TABLE IF NOT EXISTS `cuenta_bd`.`tipocuenta`
(
    `idTipoCuenta` INT         NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `codigo`       VARCHAR(4)  NOT NULL COMMENT 'Codigo del tipo de cuenta',
    `nombre`       VARCHAR(45) NOT NULL COMMENT 'Nombre del tipo de cuenta',
    `estado`       TINYINT     NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idTipoCuenta`)
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
    PRIMARY KEY (`idCuenta`, `idTipoCuenta`),
    INDEX          `fk_cuenta_tipocuenta_idx` (`idTipoCuenta` ASC) VISIBLE,
    CONSTRAINT `fk_cuenta_tipocuenta`
        FOREIGN KEY (`idTipoCuenta`)
            REFERENCES `cuenta_bd`.`tipocuenta` (`idTipoCuenta`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
COMMENT
= 'Tabla que guarda información de las cuentas del cliente';

USE `movimiento_bd` ;

-- -----------------------------------------------------
-- Table `movimiento_bd`.`tipomovimiento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movimiento_bd`.`tipomovimiento`;

CREATE TABLE IF NOT EXISTS `movimiento_bd`.`tipomovimiento`
(
    `idTipoMovimiento` INT         NOT NULL AUTO_INCREMENT COMMENT 'Identificación principal del registro',
    `codigo`           VARCHAR(4)  NOT NULL COMMENT 'Codigo del tipo de movimiento',
    `nombre`           VARCHAR(45) NOT NULL COMMENT 'Nombre del tipo de movimiento',
    `estado`           TINYINT     NOT NULL DEFAULT 1 COMMENT 'Estado del registro activo o inactivo',
    PRIMARY KEY (`idTipoMovimiento`)
)
    ENGINE = InnoDB;

-- ---------------------------------------------------
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
    PRIMARY KEY (`idMovimientoCuenta`),
    INDEX                `fk_movimientocuenta_tipomovimiento_idx` (`idTipoMovimiento` ASC) VISIBLE,
    UNIQUE INDEX `Uk_idCuenta_fechaMovimiento` (`idCuenta` ASC, `fechaMovimiento` ASC) VISIBLE,
    CONSTRAINT `fk_movimientocuenta_tipomovimiento`
        FOREIGN KEY (`idTipoMovimiento`)
            REFERENCES `movimiento_bd`.`tipomovimiento` (`idTipoMovimiento`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    COMMENT = 'Tabla que guarda los movimientos de las cuentas';

INSERT INTO persona_bd.genero (codigo, nombre, estado)
values ('M', 'Masculino', 1);
INSERT INTO persona_bd.genero (codigo, nombre, estado)
values ('F', 'Femenino', 1);


