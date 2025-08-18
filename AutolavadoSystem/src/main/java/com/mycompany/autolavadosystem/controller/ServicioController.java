/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controller;

import com.mycompany.autolavadosystem.conexion.ConexionDB;
import com.mycompany.autolavadosystem.model.ServiciosModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodo
 */
public class ServicioController {
     public static List<ServiciosModel> obtenerTodos() {
        List<ServiciosModel> lista = new ArrayList<>();
        try {
            Connection con = ConexionDB.obtenerConexion();
            String sql = "SELECT * FROM tbc_servicios";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ServiciosModel s = new ServiciosModel(
                    rs.getInt("idServicio"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getByte("estatus")
                );
                lista.add(s);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public static boolean insertarServicio(ServiciosModel servicio) {
        try {
            Connection con = ConexionDB.obtenerConexion();
            String sql = "INSERT INTO tbc_servicios (nombre, descripcion, precio, estatus) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getPrecio());
            ps.setByte(4, servicio.getEstatus());

            ps.executeUpdate();

            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarServicio(int id) {
        try {
            Connection con = ConexionDB.obtenerConexion();
            String sql = "DELETE FROM tbc_servicios WHERE idServicio = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarServicio(ServiciosModel servicio) {
        try {
            Connection con = ConexionDB.obtenerConexion();
            String sql = "UPDATE tbc_servicios SET nombre=?, descripcion=?, precio=?, estatus=? WHERE idServicio=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getPrecio());
            ps.setByte(4, servicio.getEstatus());
            ps.setInt(5, servicio.getIdServicio());

            ps.executeUpdate();

            ps.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<ServiciosModel> buscarPorNombre(String nombre) {
        List<ServiciosModel> lista = new ArrayList<>();
        try {
            Connection con = ConexionDB.obtenerConexion();
            String sql = "SELECT * FROM tbc_servicios WHERE nombre LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ServiciosModel s = new ServiciosModel(
                    rs.getInt("idServicio"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getByte("estatus")
                );
                lista.add(s);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}
