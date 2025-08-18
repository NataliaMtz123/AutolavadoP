/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.Model;

/**
 *
 * @author carlo
 */
public class RolModel {
    private int idRol;
    private String nombreRol;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    // Constructor Vacio
    public RolModel(){
        
    }
    
    // Constructor con todos los campos
    public RolModel(int idRol, String nombreRol){
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        
    }
    
    // Constructor con todos los campos menos la clave primaria
    public RolModel(String nombreRol){
        this.nombreRol = nombreRol;
        
    }
    @Override
    public String toString() {
    return nombreRol; // o el atributo que quieras mostrar
}
}
