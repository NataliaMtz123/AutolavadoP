-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: db_autolavado
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbc_usuarios`
--

DROP TABLE IF EXISTS `tbc_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbc_usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `primerApellido` varchar(45) NOT NULL,
  `segundoApellido` varchar(45) DEFAULT NULL,
  `direccion` varchar(90) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `correo` varchar(70) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `idRol` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `Fk_roles_idx` (`idRol`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`idRol`) REFERENCES `tbi_roles` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbc_usuarios`
--

LOCK TABLES `tbc_usuarios` WRITE;
/*!40000 ALTER TABLE `tbc_usuarios` DISABLE KEYS */;
INSERT INTO `tbc_usuarios` VALUES (1,'Ingrid Natalia','Martinez','Carrasco','Adolfo Lopez Mateos','7641303533','240537@utxicotepec.edu.mx','nCarrasco','123456789','2006-07-19',1);
/*!40000 ALTER TABLE `tbc_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbc_vehiculo`
--

DROP TABLE IF EXISTS `tbc_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbc_vehiculo` (
  `idVehiculo` int NOT NULL AUTO_INCREMENT,
  `matricula` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `anio` year NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `idCliente` int NOT NULL,
  PRIMARY KEY (`idVehiculo`),
  KEY `fk_clientes_idx` (`idCliente`),
  CONSTRAINT `fk_clientes` FOREIGN KEY (`idCliente`) REFERENCES `tbi_cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbc_vehiculo`
--

LOCK TABLES `tbc_vehiculo` WRITE;
/*!40000 ALTER TABLE `tbc_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbc_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbc_ventas_servicios`
--

DROP TABLE IF EXISTS `tbc_ventas_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbc_ventas_servicios` (
  `idVentasServicios` int NOT NULL AUTO_INCREMENT,
  `idUsuarioC` int NOT NULL,
  `idUsuarioO` int NOT NULL,
  `idServicio` int NOT NULL,
  `idVehiculo` int NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estatus` varchar(45) NOT NULL,
  `pagado` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idVentasServicios`),
  KEY `fk_usuarioC_idx` (`idUsuarioC`),
  KEY `fk_usuarioO_idx` (`idUsuarioO`),
  KEY `fk_servicio_idx` (`idServicio`),
  KEY `fk_vehiculo_idx` (`idVehiculo`),
  CONSTRAINT `fk_servicio` FOREIGN KEY (`idServicio`) REFERENCES `tbi_servicios` (`idServicios`),
  CONSTRAINT `fk_usuarioC` FOREIGN KEY (`idUsuarioC`) REFERENCES `tbc_usuarios` (`idUsuario`),
  CONSTRAINT `fk_usuarioO` FOREIGN KEY (`idUsuarioO`) REFERENCES `tbc_usuarios` (`idUsuario`),
  CONSTRAINT `fk_vehiculo` FOREIGN KEY (`idVehiculo`) REFERENCES `tbc_vehiculo` (`idVehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbc_ventas_servicios`
--

LOCK TABLES `tbc_ventas_servicios` WRITE;
/*!40000 ALTER TABLE `tbc_ventas_servicios` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbc_ventas_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbi_cliente`
--

DROP TABLE IF EXISTS `tbi_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbi_cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `primerApellido` varchar(45) NOT NULL,
  `segundoApellido` varchar(45) DEFAULT NULL,
  `direccion` varchar(90) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `correo` varchar(70) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbi_cliente`
--

LOCK TABLES `tbi_cliente` WRITE;
/*!40000 ALTER TABLE `tbi_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbi_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbi_roles`
--

DROP TABLE IF EXISTS `tbi_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbi_roles` (
  `idRol` int NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(45) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbi_roles`
--

LOCK TABLES `tbi_roles` WRITE;
/*!40000 ALTER TABLE `tbi_roles` DISABLE KEYS */;
INSERT INTO `tbi_roles` VALUES (1,'Administrador'),(2,'Cajero'),(3,'Obrero');
/*!40000 ALTER TABLE `tbi_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbi_servicios`
--

DROP TABLE IF EXISTS `tbi_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbi_servicios` (
  `idServicios` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(90) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `estatus` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`idServicios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbi_servicios`
--

LOCK TABLES `tbi_servicios` WRITE;
/*!40000 ALTER TABLE `tbi_servicios` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbi_servicios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-23 16:24:40
