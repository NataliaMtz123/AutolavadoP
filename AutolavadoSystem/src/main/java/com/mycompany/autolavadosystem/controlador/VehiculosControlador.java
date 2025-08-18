/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controlador;

import com.mycompany.autolavadosystem.Conexion.ConexionDB;
import com.mycompany.autolavadosystem.Model.VehiculosModel;
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
public class VehiculosControlador {
    
    public static boolean insertarVehiculo(VehiculosModel vehiculo){
        String sql = "INSERT INTO tbc_vehiculo(matricula,marca,modelo,color,anio,tipo,idCliente) VALUES (?,?,?,?,?,?,?)";
        
        try(Connection con = ConexionDB.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, vehiculo.getMatricula());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());
            ps.setString(5, vehiculo.getAnio());
            ps.setString(6, vehiculo.getTipo());
            ps.setInt(7, vehiculo.getIdCliente());
            
            ps.executeUpdate();
            return true;
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean eliminarVehiculo(int idVehiculo){
        String sql = "DELETE FROM tbc_vehiculo WHERE idVehiculo = ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idVehiculo);
            ps.executeUpdate();
            return true;
            
        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<VehiculosModel> obtenerTodosVehiculos(){
        var lista = new ArrayList<VehiculosModel>();
        String sql = "SELECT * FROM tbc_vehiculo";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                lista.add(new VehiculosModel(
                rs.getInt("idVehiculo"),
                rs.getString("matricula"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getString("color"),
                rs.getString("anio"),
                rs.getString("tipo"),
                rs.getInt("idCliente")));    
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
        return lista;
    }
    
    public static List<VehiculosModel> buscarVehiculoPorMatricula(String matriculaBusqueda){
        List<VehiculosModel> listaVehiculos = new ArrayList<>();
        String sql = "SELECT * FROM tbc_vehiculo WHERE matricula LIKE ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,"%"+matriculaBusqueda+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VehiculosModel vehiculo = new VehiculosModel();
                vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setColor(rs.getString("color"));
                vehiculo.setAnio(rs.getString("anio"));
                vehiculo.setTipo(rs.getString("tipo"));
                vehiculo.setIdCliente(rs.getInt("idCliente"));
                listaVehiculos.add(vehiculo);
            }
        }catch(SQLException ex){
            ex.printStackTrace();            
        }
        return listaVehiculos;
    }
    
    public static boolean actualizarVehiculo(VehiculosModel vehiculo){
        String sql = "UPDATE tbc_vehiculo SET matricula = ?, marca = ?, modelo = ?, color = ?, anio = ?, tipo = ?, idCliente = ? WHERE idVehiculo = ?";
        boolean actualizado = false;
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, vehiculo.getMatricula());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());
            ps.setString(5, vehiculo.getAnio());
            ps.setString(6, vehiculo.getTipo());
            ps.setInt(7, vehiculo.getIdCliente());
            ps.setInt(8, vehiculo.getIdVehiculo());
            
            int filasAfectadas = ps.executeUpdate();
            actualizado = filasAfectadas > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();  
        }
        return actualizado;
    }
   
}
