/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controlador;

import com.mycompany.autolavadosystem.Conexion.ConexionDB;
import com.mycompany.autolavadosystem.Model.UsuarioModel;
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
public class UsuarioControlador {
    public static boolean insertarUsuario(UsuarioModel usuario){
        String sql = "INSERT INTO tbc_usuarios(nombre,primerApellido,segundoApellido,direccion,telefono,correo,"
                +"usuario,password,fechaNacimiento,idRol) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try(Connection con = ConexionDB.ObtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getSegundoApellido());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setString(7, usuario.getUsuario());
            ps.setString(8, usuario.getPassword());
            ps.setString(9, usuario.getFechaNacimiento());
            ps.setInt(10, usuario.getIdRol());
            
            ps.executeUpdate();
            return true;
            
        }catch(SQLException ex){
            ex.printStackTrace();
        return false;
    }
    
}
    public static boolean eliminarUsuario(int idUsuario){
        String sql = "DELETE FROM tbc_usuarios WHERE idUsuario = ?";
        
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            return true; 
        }catch(SQLException ex){
            ex.printStackTrace();
        
        return false;
        }
    }
    
    public static List<UsuarioModel> obtenerTodos(){
         var lista = new ArrayList<UsuarioModel>();
         String sql = "SELECT * FROM tbc_usuarios";
         try(Connection con = ConexionDB.ObtenerConexion();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)){
             while(rs.next()){
                 lista.add(new UsuarioModel(
                 rs.getInt("idUsuario"),
                 rs.getString("nombre"),
                 rs.getString("primerApellido"),
                 rs.getString("SegundoApellido"),
                 rs.getString("Direccion"),
                 rs.getString("telefono"),
                 rs.getString("correo"),
                 rs.getString("usuario"),
                 rs.getString("password"),
                 rs.getString("fechaNacimiento"),
                 rs.getInt("idRol")));
             }
         }catch(SQLException ex){
             ex.printStackTrace();
             
         }
        return lista;
    }
    
    public static List<UsuarioModel> obtenerLavador(){
         var lista = new ArrayList<UsuarioModel>();
         String sql = "SELECT * FROM tbc_usuarios WHERE idRol = 3";
         try(Connection con = ConexionDB.ObtenerConexion();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)){
             while(rs.next()){
                 lista.add(new UsuarioModel(
                 rs.getInt("idUsuario"),
                 rs.getString("nombre"),
                 rs.getString("primerApellido"),
                 rs.getString("SegundoApellido"),
                 rs.getString("Direccion"),
                 rs.getString("telefono"),
                 rs.getString("correo"),
                 rs.getString("usuario"),
                 rs.getString("password"),
                 rs.getString("fechaNacimiento"),
                 rs.getInt("idRol")));
             }
         }catch(SQLException ex){
             ex.printStackTrace();
             
         }
        return lista;
    }
    
    public static List<UsuarioModel> obtenerCajero(){
    var lista = new ArrayList<UsuarioModel>();
         String sql = "SELECT * FROM tbc_usuarios WHERE idRol = 2";
         try(Connection con = ConexionDB.ObtenerConexion();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)){
             while(rs.next()){
                 lista.add(new UsuarioModel(
                 rs.getInt("idUsuario"),
                 rs.getString("nombre"),
                 rs.getString("primerApellido"),
                 rs.getString("SegundoApellido"),
                 rs.getString("Direccion"),
                 rs.getString("telefono"),
                 rs.getString("correo"),
                 rs.getString("usuario"),
                 rs.getString("password"),
                 rs.getString("fechaNacimiento"),
                 rs.getInt("idRol")));
             }
         }catch(SQLException ex){
             ex.printStackTrace();
             
         }
        return lista;
    }    

    public static List<UsuarioModel> buscarUsuarioPorNombre(String nombreBusqueda){
       List<UsuarioModel> listaUsuarios = new ArrayList<>();
       String sql = "SELECT * FROM tbc_usuarios WHERE nombre LIKE ?";
       
       try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
           ps.setString(1,"%"+nombreBusqueda+"%");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               UsuarioModel usuario = new UsuarioModel();
               usuario.setIdUsuario(rs.getInt("idUsuario"));
               usuario.setNombre(rs.getString("nombre"));
               usuario.setPrimerApellido(rs.getString("primerApellido"));
               usuario.setSegundoApellido(rs.getString("segundoApellido"));
               usuario.setDireccion(rs.getString("direccion"));
               usuario.setTelefono(rs.getString("telefono"));
               usuario.setCorreo(rs.getString("correo"));
               usuario.setUsuario(rs.getString("usuario"));
               usuario.setPassword(rs.getString("password"));
               usuario.setFechaNacimiento(rs.getString("fechaNacimiento"));
               usuario.setIdRol(rs.getInt("idRol"));
               listaUsuarios.add(usuario);
           }
        }catch(SQLException ex){
            ex.printStackTrace();
       }
       return listaUsuarios;
       }
    public static boolean actualizarUsuario(UsuarioModel usuario){
        String sql = "UPDATE tbc_usuarios SET nombre = ?, primerApellido = ?, segundoApellido = ?, direccion = ?, telefono = ?, correo = ?,"
                + "usuario = ?, password = ?, fechaNacimiento = ?, idRol = ? WHERE idUsuario = ?";
        boolean actualizado = false;
        try(Connection con = ConexionDB.ObtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPrimerApellido());
            ps.setString(3, usuario.getSegundoApellido());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreo());
            ps.setString(7, usuario.getUsuario());
            ps.setString(8, usuario.getPassword());
            ps.setString(9, usuario.getFechaNacimiento());
            ps.setInt(10, usuario.getIdRol());
            ps.setInt(11, usuario.getIdUsuario());
            
            int filasAfectadas = ps.executeUpdate();
            actualizado = filasAfectadas > 0;
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }   
        return actualizado;
    
}
    public boolean verificarCredenciales(String usuario, String password){
        String sql = "SELECT * FROM tbc_usuarios WHERE usuario = ? AND password = ?";
        try(Connection con = ConexionDB.ObtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, usuario);
            ps.setString(2, password);
            try(ResultSet rs  = ps.executeQuery()){
             return rs.next();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        
    }
}
