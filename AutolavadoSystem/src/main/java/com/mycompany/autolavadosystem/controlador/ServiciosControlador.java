/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controlador;

import com.mycompany.autolavadosystem.Conexion.ConexionDB;
import com.mycompany.autolavadosystem.Model.ServiciosModel;
import java.math.BigDecimal;
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
public class ServiciosControlador {
    
    public static boolean insertarServicio(ServiciosModel servicio){
        String sql = "INSERT INTO tbi_servicios(nombre,descripcion,precio,estatus) VALUES (?,?,?,?)";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setBigDecimal(3, servicio.getPrecio());
            ps.setInt(4, servicio.getEstatus());
            
            ps.executeUpdate();
            return true;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
       }
    }
    
    public static boolean eliminarServicio(int idServicio){
        String sql = "DELETE FROM tbi_servicios WHERE idServicios = ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idServicio);
            ps.executeUpdate();
            return true;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
     public static List<ServiciosModel> obtenerTodosServicios(){
         var lista = new ArrayList<ServiciosModel>();
         String sql = "SELECT * FROM tbi_servicios";
         try(Connection con = ConexionDB.ObtenerConexion();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)){
             while(rs.next()){
                 lista.add(new ServiciosModel(
                 rs.getInt("idServicios"),
                 rs.getString("nombre"),
                 rs.getString("descripcion"),
                 rs.getBigDecimal("precio"),
                 rs.getInt("estatus")));
             }
         }catch(SQLException ex){
             ex.printStackTrace();
         }
        return lista;
    }
     public static List<ServiciosModel> buscarServiciosPorNombre(String nombreBusqueda){
         List<ServiciosModel> listaServicios = new ArrayList<>();
         String sql = "SELECT * FROM tbi_servicios WHERE nombre LIkE  ?";
         
         try(Connection con = ConexionDB.ObtenerConexion();
                 PreparedStatement ps = con.prepareStatement(sql)){
         ps.setString(1,"%"+nombreBusqueda+"%");
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ServiciosModel servicio = new ServiciosModel();
             servicio.setIdServicio(rs.getInt("idServicios"));
             servicio.setNombre(rs.getString("nombre"));
             servicio.setDescripcion(rs.getString("descripcion"));
             servicio.setPrecio(rs.getBigDecimal("precio"));
             servicio.setEstatus(rs.getInt("estatus"));
             listaServicios.add(servicio);
             
         }   
         }catch(SQLException ex){
             ex.printStackTrace();
         }
         return listaServicios;    
         }
    
     public static boolean actualizarServicio(ServiciosModel servicio){
         String sql = "UPDATE tbi_servicios SET nombre = ?, descripcion = ?, precio = ?, estatus =? WHERE idServicios = ?";
         boolean actualizado = false;
         
         try(Connection con = ConexionDB.ObtenerConexion();
                 PreparedStatement ps = con.prepareStatement(sql)){
             
             ps.setString(1, servicio.getNombre());
             ps.setString(2, servicio.getDescripcion());
             ps.setBigDecimal(3, servicio.getPrecio());
             ps.setInt(4, servicio.getEstatus());
             ps.setInt(5, servicio.getIdServicio());
             
             int filasAfectadas = ps.executeUpdate();
             actualizado = filasAfectadas > 0;
   
         }catch(SQLException ex){
             ex.printStackTrace();
             
         }
         return actualizado;
     }
     
}
