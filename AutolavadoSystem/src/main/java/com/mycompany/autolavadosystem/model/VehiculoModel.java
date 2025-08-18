/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.model;

/**
 *
 * @author Rodo
 */
public class VehiculoModel {
    private int idVehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int anio;
    private String tipo;
    private String cliente;

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }


    public VehiculoModel(){
        
    }
    
    // Constructor con ID (para modificar)
    public VehiculoModel(int idVehiculo, String matricula, String marca, String modelo, String color, int anio, String tipo, String cliente) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.tipo = tipo;
        this.cliente = cliente;
    }
    
    // Constructor sin ID (para insertar)
    public VehiculoModel(String matricula, String marca, String modelo, String color, int anio, String tipo, String cliente) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.tipo = tipo;
        this.cliente = cliente;
    }

}

