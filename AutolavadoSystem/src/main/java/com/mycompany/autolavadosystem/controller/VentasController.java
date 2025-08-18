/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controller;

import com.mycompany.autolavadosystem.conexion.ConexionDB;
import com.mycompany.autolavadosystem.model.VentasModel;
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
public class VentasController {
    public static boolean insertarVenta(VentasModel venta) {
        String sql = "INSERT INTO ventas (cajero, lavador, servicio, vehiculo, fecha, hora, estatus, pago) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, venta.getCajero());
            ps.setString(2, venta.getLavador());
            ps.setString(3, venta.getServicio());
            ps.setString(4, venta.getVehiculo());
            ps.setString(5, venta.getFecha());
            ps.setString(6, venta.getHora());
            ps.setString(7, venta.getEstatus());
            ps.setString(8, venta.getPago());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<VentasModel> obtenerTodas() {
        List<VentasModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection con = ConexionDB.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                VentasModel v = new VentasModel(
                        rs.getInt("idVenta"),
                        rs.getString("cajero"),
                        rs.getString("lavador"),
                        rs.getString("servicio"),
                        rs.getString("vehiculo"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("estatus"),
                        rs.getString("pago")
                );
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean eliminarVenta(int id) {
        String sql = "DELETE FROM ventas WHERE idVenta = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarVenta(VentasModel venta) {
        String sql = "UPDATE ventas SET cajero=?, lavador=?, servicio=?, vehiculo=?, fecha=?, hora=?, estatus=?, pago=? WHERE idVenta=?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, venta.getCajero());
            ps.setString(2, venta.getLavador());
            ps.setString(3, venta.getServicio());
            ps.setString(4, venta.getVehiculo());
            ps.setString(5, venta.getFecha());
            ps.setString(6, venta.getHora());
            ps.setString(7, venta.getEstatus());
            ps.setString(8, venta.getPago());
            ps.setInt(9, venta.getIdVenta());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<VentasModel> buscarPorCajero(String cajero) {
        List<VentasModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE cajero LIKE ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + cajero + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VentasModel v = new VentasModel(
                        rs.getInt("idVenta"),
                        rs.getString("cajero"),
                        rs.getString("lavador"),
                        rs.getString("servicio"),
                        rs.getString("vehiculo"),
                        rs.getString("fecha"),
                        rs.getString("hora"),
                        rs.getString("estatus"),
                        rs.getString("pago")
                );
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

