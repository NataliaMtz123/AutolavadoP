/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.Model;

import java.math.BigDecimal;

/**
 *
 * @author carlo
 */
public class ServiciosModel {


    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

   
    
    
    private int idServicio;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int estatus;

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    
    public ServiciosModel(){
        
    }
    
    public ServiciosModel(int idServicio, String nombre, String descripcion, BigDecimal precio, int estatus){
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estatus = estatus;        
    }
    
     public ServiciosModel(String nombre, String descripcion, BigDecimal precio, int estatus){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estatus = estatus;
        
    }
     
       @Override
    public String toString() {
    return nombre; // o el atributo que quieras mostrar
}
    
}
