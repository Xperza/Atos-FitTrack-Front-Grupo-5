-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fit_track
-- -----------------------------------------------------
DROP DATABASE IF EXISTS fit_track;
CREATE DATABASE IF NOT EXISTS fit_track;
use fit_track;
-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Usuario` (
  `id` INT AUTO_INCREMENT,
  `password` VARCHAR(80) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(200) NOT NULL,
  `email` VARCHAR(254) NOT NULL UNIQUE,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Objetivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Objetivo` (
  `id` INT AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  `descripcion` TEXT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_final` DATE NULL,
  `id_usuario` INT NOT NULL,
  `id_ejercicio` INT,
  `id_dieta` INT,
  `id_sueño` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Objetivo_Usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`),
   CONSTRAINT `fk_dieta`
    FOREIGN KEY (`id_dieta`)
    REFERENCES `Dieta` (`id`),
   CONSTRAINT `fk_sueño`
    FOREIGN KEY (`id_sueño`)
    REFERENCES `Sueño` (`id`),
   CONSTRAINT `fk_ejercicio`
    FOREIGN KEY (`id_ejercicio`)
    REFERENCES `Ejercicio` (`id`)
    );


-- -----------------------------------------------------
-- Table `Ejercicio`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Ejercicio` (
  `id` INT AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  `descripcion` VARCHAR(254) NULL,
  `musculos` TEXT NOT NULL,
  `repeticiones` INT NOT NULL,
  `series` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Ejercicio_Usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`));
    
    
CREATE TABLE IF NOT EXISTS `Ejercicio_VR` (
  `id` INT AUTO_INCREMENT,
  `id_video` INT NOT NULL,
  `calorias_quemadas` DOUBLE NOT NULL,
  `fecha` DATE NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_EjercicioVR_Usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`),
  CONSTRAINT `fk_videoVR`
    FOREIGN KEY (`id_video`)
    REFERENCES `Video_VR` (`id`));
    
CREATE TABLE IF NOT EXISTS `Video_VR` (
  `id` INT AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  `descripcion` TEXT NULL,
  `calorias` DOUBLE NOT NULL,
  `duracion` TIME NOT NULL,
  `video` VARCHAR(254) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `Alimentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Alimento` (
  `id` INT AUTO_INCREMENT,
  `nombre` VARCHAR(254) NOT NULL,
  `ingredientes`TEXT NOT NULL,
  `alergenos` VARCHAR(254) NULL,
  `calorias` DOUBLE NOT NULL,
  `valor_nutricional` TEXT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Post` (
  `id` INT AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  `descripcion` TEXT NULL,
  `fecha` DATETIME NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Post_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`));


-- -----------------------------------------------------
-- Table `Comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Comentario` (
  `id` INT AUTO_INCREMENT,
  `contenido` TEXT NOT NULL,
  `fecha` DATETIME NOT NULL,
  `id_post` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Comentario_Post1`
    FOREIGN KEY (`id_post`)
    REFERENCES `Post` (`id`),
CONSTRAINT `fk_Comentario_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`));


-- -----------------------------------------------------
-- Table `Sueño`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sueño` (
  `id` INT AUTO_INCREMENT,
  `hora_inicial` TIME NOT NULL,
  `hora_final` TIME NOT NULL,
  `calidad_sueño` VARCHAR(45) NULL,
  `comentario` VARCHAR(254) NULL,
  `fecha` DATE NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Sueño_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`));


-- -----------------------------------------------------
-- Table `Calendario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Calendario` (
  `id` INT AUTO_INCREMENT,
  `fecha_inicio` DATE NOT NULL,
  `fecha_final` DATE NOT NULL,
  `datos` VARCHAR(254) ,
  `id_usuario` INT NOT NULL,
  `id_ejercicio` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Calendario_Ejercicio`
    FOREIGN KEY (`id_ejercicio`)
    REFERENCES `Ejercicio` (`id`),
    CONSTRAINT `fk_Calendario_Usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`));


-- -----------------------------------------------------
-- Table `Alimentos_has_Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dieta` (
  `id` INT AUTO_INCREMENT,
  `nombre` VARCHAR(254) NOT NULL,
  `fecha` DATE NOT NULL,
  `observaciones` VARCHAR(254),
  `cheat_day` VARCHAR(15),
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Alimentos_has_Usuario_Usuario1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Usuario` (`id`));
    
    CREATE TABLE IF NOT EXISTS `DietaAlimento` (
	`id` INT AUTO_INCREMENT,
    `id_dieta` INT NOT NULL,
    `id_alimento` INT NOT NULL,
    `tipo` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
  CONSTRAINT `fk_dieta_DietaAlimento`
    FOREIGN KEY (`id_dieta`)
    REFERENCES `Dieta` (`id`),
     CONSTRAINT `fk_alimento_DietaAlimento`
    FOREIGN KEY (`id_alimento`)
    REFERENCES `Alimento` (`id`));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;