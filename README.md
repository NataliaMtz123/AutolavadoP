# 🚗💦 Sistema de Autolavado en Java

Este proyecto es un **sistema de gestión para un autolavado**, desarrollado en **Java** con **Swing** para la interfaz gráfica (GUI) y **MySQL** como sistema de base de datos.  
El sistema permite administrar usuarios (cajeros), vehículos, servicios y ventas.

---

## 📑 Índice

1. [Características principales](#-características-principales)  
2. [Tecnologías utilizadas](#️-tecnologías-utilizadas)  
3. [Estructura del proyecto](#-estructura-del-proyecto)  
4. [Instalación y configuración](#️-instalación-y-configuración)  
5. [Script SQL de la base de datos](#-script-sql-de-la-base-de-datos)  
6. [Ejemplo de conexión en Java](#-ejemplo-de-conexión-en-java)  

---

## 📌 Características principales

- Registro, actualización y eliminación de **usuarios (cajeros)**.  
- Registro y administración de **vehículos de clientes**.  
- Control de **ventas** con cálculo automático de subtotal, IVA y total.  
- Interfaz gráfica amigable con **Java Swing**.  
- Conexión a **base de datos MySQL**.  
- Uso de **JComboBox** para la selección de datos relacionados.  

---

## 🛠️ Tecnologías utilizadas

- **Java SE 8+**  
- **Swing** (Interfaz gráfica)  
- **MySQL** (Base de datos)  
- **JDBC** (Conexión con la base de datos)  
- **NetBeans / IntelliJ IDEA / Eclipse** (IDE recomendado)  

---

## 📂 Estructura del proyecto


---

## ⚙️ Instalación y configuración

1. Descargar o clonar el proyecto en tu equipo.  
2. Abrirlo en tu IDE preferido (NetBeans, IntelliJ o Eclipse).  
3. Crear la base de datos en MySQL (script incluido abajo).  
4. Configurar los datos de conexión en `Conexion.java`.  
5. Ejecutar la clase principal `Main.java`.  

---

## 📊 Script SQL de la base de datos

```sql
-- Crear la base de datos
CREATE DATABASE autolavado;
USE autolavado;

-- Tabla de usuarios (cajeros)
CREATE TABLE tbc_usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  usuario VARCHAR(50) NOT NULL UNIQUE,
  contraseña VARCHAR(100) NOT NULL
);

-- Tabla de vehículos
CREATE TABLE tbc_vehiculos (
  id_vehiculo INT AUTO_INCREMENT PRIMARY KEY,
  placa VARCHAR(20) NOT NULL UNIQUE,
  marca VARCHAR(50),
  modelo VARCHAR(50),
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES tbc_usuarios(id_usuario)
);

-- Tabla de servicios
CREATE TABLE tbc_servicios (
  id_servicio INT AUTO_INCREMENT PRIMARY KEY,
  nombre_servicio VARCHAR(100) NOT NULL,
  precio DECIMAL(10,2) NOT NULL
);

-- Tabla de ventas
CREATE TABLE tbc_ventas (
  id_venta INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  id_vehiculo INT,
  id_servicio INT,
  subtotal DECIMAL(10,2),
  iva DECIMAL(10,2),
  total DECIMAL(10,2),
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (id_usuario) REFERENCES tbc_usuarios(id_usuario),
  FOREIGN KEY (id_vehiculo) REFERENCES tbc_vehiculos(id_vehiculo),
  FOREIGN KEY (id_servicio) REFERENCES tbc_servicios(id_servicio)
);



package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/autolavado";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";

    public static Connection getConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("❌ Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
