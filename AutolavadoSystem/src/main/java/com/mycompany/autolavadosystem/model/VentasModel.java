/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.Model;

/**
 *
 * @author carlo
 */
public class VentasModel {
    private int idVentaServicio;
    private int idCajero;
    private int idLavador;
    private int idServicio;
    private int idVehiculo;
    private String fecha;
    private String hora;
    private Object estatus;

    public Object getEstatus() {
        return estatus;
    }

    public void setEstatus(Object estatus) {
        this.estatus = estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    private int pagado;

    public int getIdVentaServicio() {
        return idVentaServicio;
    }

    public void setIdVentaServicio(int idVentaServicio) {
        this.idVentaServicio = idVentaServicio;
    }

    public int getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(int idCajero) {
        this.idCajero = idCajero;
    }

    public int getIdLavador() {
        return idLavador;
    }

    public void setIdLavador(int idLavador) {
        this.idLavador = idLavador;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

  
    public VentasModel(){
        
    }
      public VentasModel(int idVentaServicio, int idCajero, int idLavador, int idServicio, int idVehiculo, String fecha,
            String hora, Object estatus, int pagado) {
        this.idVentaServicio = idVentaServicio;
        this.idCajero = idCajero;
        this.idLavador = idLavador;
        this.idServicio = idServicio;
        this.idVehiculo = idVehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.pagado = pagado;
    }
      public VentasModel(int idCajero, int idLavador, int idServicio, int idVehiculo, String fecha,
            String hora, Object estatus, int pagado) {
        this.idCajero = idCajero;
        this.idLavador = idLavador;
        this.idServicio = idServicio;
        this.idVehiculo = idVehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.pagado = pagado;
    }
      
       /* @Override
    public String toString() {
    return ; // o el atributo que quieras mostrar
}
 */  
    
    
}
