/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controlador;

import com.mycompany.autolavadosystem.Conexion.ConexionDB;
import com.mycompany.autolavadosystem.Model.RolModel;
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
public class RolesControlador {

    public static boolean insertarRol(RolModel rol) {
        String sql = "INSERT INTO tbi_roles (nombreRol) VALUES (?)";
        try (Connection con = ConexionDB.ObtenerConexion(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol.getNombreRol());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<RolModel>obtenerTodos(){
        var lista = new ArrayList<RolModel>();
        String sql = "SELECT * FROM tbi_roles";
        try (Connection con = ConexionDB.ObtenerConexion(); 
                Statement st = con.createStatement(); 
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new RolModel(rs.getInt("idRol"), rs.getString("nombreRol")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
     public static boolean eliminarRol(int idRol) {
        String sql = "DELETE FROM tbi_roles WHERE idRol = ?";
        try (Connection con = ConexionDB.ObtenerConexion(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
