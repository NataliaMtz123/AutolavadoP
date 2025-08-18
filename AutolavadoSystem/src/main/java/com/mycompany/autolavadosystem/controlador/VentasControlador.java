/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controlador;

import com.mycompany.autolavadosystem.Conexion.ConexionDB;
import com.mycompany.autolavadosystem.Model.VentasModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class VentasControlador {
    
     public static boolean InsertarVentas(VentasModel venta){
        String sql = "INSERT INTO tbc_ventas_servicios(idUsuarioC,idUsuarioO,idServicio,idVehiculo,fecha,hora,estatus,pagado) VALUES (?,?,?,?,?,?,?,?)";
        
        try(Connection con = ConexionDB.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, venta.getIdCajero());
            ps.setInt(2, venta.getIdLavador());
            ps.setInt(3, venta.getIdServicio());
            ps.setInt(4, venta.getIdVehiculo());
            ps.setString(5, venta.getFecha());
            ps.setString(6, venta.getHora());
            ps.setObject(7, venta.getEstatus());
            ps.setInt(8, venta.getPagado());
            ps.executeUpdate();
            return true;
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean eliminarVenta(int idVenta){
        String sql = "DELETE FROM tbc_ventas_servicios WHERE idVentasServicios = ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idVenta);
            ps.executeUpdate();
            return true;
            
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<VentasModel> obtenerTodasVentas(){
        var lista = new ArrayList<VentasModel>();
        String sql = "SELECT * FROM tbc_ventas_servicios";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(new VentasModel(
                rs.getInt("idVentasServicios"),
                rs.getInt("idUsuarioc"),
                rs.getInt("idUsuarioO"),
                rs.getInt("idServicio"),
                rs.getInt("idVehiculo"),
                rs.getString("Fecha"),
                rs.getString("hora"),
                rs.getString("estatus"),
                rs.getInt("pagado")));    
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
        return lista;
    }
    
    public static List<VentasModel> buscarVentaPorMatricula(String idVehiculo){
        List<VentasModel> listaVentas = new ArrayList<>();
        String sql = "SELECT * FROM tbc_ventas_servicios WHERE idVehiculo LIKE ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            //ps.setInt(1, idVehiculo);
            ps.setString(1,"%"+idVehiculo+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VentasModel ventas = new VentasModel();
                ventas.setIdVentaServicio(rs.getInt("idVentasServicios"));
                ventas.setIdCajero(rs.getInt("idUsuarioC"));
                ventas.setIdLavador(rs.getInt("idUsuarioO"));
                ventas.setIdServicio(rs.getInt("idServicio"));
                ventas.setIdVehiculo(rs.getInt("idVehiculo"));
                ventas.setFecha(rs.getString("fecha"));
                ventas.setHora(rs.getString("hora"));
                ventas.setEstatus(rs.getString("estatus"));
                ventas.setPagado(rs.getInt("pagado"));
                
                listaVentas.add(ventas);
            }
        }catch(SQLException ex){
            ex.printStackTrace();            
        }
        return listaVentas;
    }
    
    public static boolean actualizarVentas(VentasModel venta){
        String sql = "UPDATE tbc_ventas_servicios SET idUsuarioC = ?, idUsuarioO = ?, idServicio = ?, idVehiculo = ?, fecha = ?, hora = ?, estatus = ?, pagado = ? WHERE idVentasServicios = ?";
        boolean actualizado = false;
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, venta.getIdCajero());
            ps.setInt(2, venta.getIdLavador());
            ps.setInt(3, venta.getIdServicio());
            ps.setInt(4, venta.getIdVehiculo());
            ps.setString(5, venta.getFecha());
            ps.setString(6, venta.getHora());
            ps.setObject(7, venta.getEstatus());
            ps.setInt(8, venta.getPagado());
            
            int filasAfectadas = ps.executeUpdate();
            actualizado = filasAfectadas > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();  
        }
        return actualizado;
    }  
}
