/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controlador;

import com.mycompany.autolavadosystem.Conexion.ConexionDB;
import com.mycompany.autolavadosystem.Model.ClientesModel;
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
public class ClientesControlador {
    
    public static boolean insertarCliente(ClientesModel cliente){
        String sql= "INSERT INTO tbi_cliente(nombre,primerApellido,segundoApellido,direccion,telefono,correo,password)"
                +"VALUES (?,?,?,?,?,?,?)";
        try(Connection con = ConexionDB.ObtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPrimerApellido());
            ps.setString(3, cliente.getSegundoApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getPassword());
            
            ps.executeUpdate();
            return true;
            
         }catch(SQLException ex){
            ex.printStackTrace();
            return false;
       }
    }
    
    public static boolean eliminarCliente(int idCliente){
        String sql = "DELETE FROM tbi_cliente WHERE idCliente = ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            return true; 
        }catch(SQLException ex){
            ex.printStackTrace();
        
        return false;
        }
    }
     public static List<ClientesModel> obtenerTodosClientes(){
         var lista = new ArrayList<ClientesModel>();
         String sql = "SELECT * FROM tbi_cliente";
         try(Connection con = ConexionDB.ObtenerConexion();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)){
             while(rs.next()){
                 lista.add(new ClientesModel(
                 rs.getInt("idCliente"),
                 rs.getString("nombre"),
                 rs.getString("primerApellido"),
                 rs.getString("SegundoApellido"),
                 rs.getString("Direccion"),
                 rs.getString("telefono"),
                 rs.getString("correo"),
                 rs.getString("password")));
             }
         }catch(SQLException ex){
             ex.printStackTrace();
             
         }
        return lista;
    }
    
     public static List<ClientesModel> buscarClientePorNombre(String nombreBusqueda){
         List<ClientesModel> listaClientes = new ArrayList<>();
         String sql = "SELECT * FROM tbi_cliente WHERE nombre LIkE  ?";
         
         try(Connection con = ConexionDB.ObtenerConexion();
                 PreparedStatement ps = con.prepareStatement(sql)){
         ps.setString(1,"%"+nombreBusqueda+"%");
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             ClientesModel cliente = new ClientesModel();
             cliente.setIdCliente(rs.getInt("idCliente"));
             cliente.setNombre(rs.getString("nombre"));
             cliente.setPrimerApellido(rs.getString("primerApellido"));
             cliente.setSegundoApellido(rs.getString("segundoApellido"));
             cliente.setDireccion(rs.getString("direccion"));
             cliente.setTelefono(rs.getString("telefono"));
             cliente.setCorreo(rs.getString("correo"));
             cliente.setPassword(rs.getString("password"));
             listaClientes.add(cliente);
             
         }   
         }catch(SQLException ex){
             ex.printStackTrace();
         }
         return listaClientes;    
         }
     
     public static boolean actualizarCliente(ClientesModel cliente){
         String sql = "UPDATE tbi_cliente SET nombre = ?, primerApellido = ?, segundoApellido = ?, direccion =?, telefono = ?,"
                 + "correo = ?, password = ? WHERE idCliente = ?";
         boolean actualizado = false;
         
         try(Connection con = ConexionDB.ObtenerConexion();
                 PreparedStatement ps = con.prepareStatement(sql)){
             
             ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getPrimerApellido());
             ps.setString(3, cliente.getSegundoApellido());
             ps.setString(4, cliente.getDireccion());
             ps.setString(5, cliente.getTelefono());
             ps.setString(6, cliente.getCorreo());
             ps.setString(7, cliente.getPassword());
             ps.setInt(8, cliente.getIdCliente());
             
             int filasAfectadas = ps.executeUpdate();
             actualizado = filasAfectadas > 0;
   
         }catch(SQLException ex){
             ex.printStackTrace();
             
         }
         return actualizado;
     }
     
}
    